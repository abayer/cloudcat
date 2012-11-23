/**
 * Licensed to Cloudera, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Cloudera, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cloudstack.reporting

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory

class ReportRunController {
    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = params.sort ?: "id"
        params.order = params.order ?: "desc"
        [reportRunList: ReportRun.list(params), reportRunTotal: ReportRun.count()]
    }

    def report() {
        params.sort = params.sort ?: "total"
        params.order = params.order ?: "desc"
        def latestReportRunId = ReportRun.where { }.projections { max 'id' }.find()
        def reportRunId = params.reportRunId ?: latestReportRunId

        def latestReportRun = ReportRun.get(reportRunId)
        
        def instanceSizes = Instance.executeQuery('select distinct serviceOfferingName from Instance')
        //        def accounts = Instance.where { }.projections { distinct: 'account' }
        def accounts = Instance.executeQuery('select distinct account from Instance')
        
        def rawInstCounts = Instance.executeQuery("select i.account, i.serviceOfferingName, count(i) from ReportRun as r join r.instances as i where r.id = ${reportRunId} and i.state = 'Running' group by i.account, i.serviceOfferingName")
        def accountInstancesMap = [:]

        accounts.each { a ->
            accountInstancesMap[a] = [:]
            
            accountInstancesMap[a]['account'] = a
            accountInstancesMap[a]['total'] = 0
        }
        rawInstCounts.each { r ->
            accountInstancesMap[r[0]][r[1]] = r[2]
            accountInstancesMap[r[0]]['total'] += r[2]
        }

        def totalInstances = [:]
        instanceSizes.each { s -> totalInstances[s] = 0 }
        totalInstances['total'] = 0
        
        accountInstancesMap.each { acct, s ->
            s.each { k, v ->
                if (k != 'account')
                    totalInstances[k] += v
            }
        }

        def topSizes = totalInstances.sort { a, b -> b.value <=> a.value }.keySet().toList().findAll { it != "total" }[0..4]
        
        log.warn("topSizes: ${topSizes}")
        def totalOther = 0
        
        totalInstances.each { k, v ->
            if (!(k in topSizes) && k != "other" && k != "total") {
                totalOther += v
            }
        }
        totalInstances["other"] = totalOther
        
        accountInstancesMap.each { acct, s ->
            def acctOther = 0
            s.each { k, v ->
                if (!(k in topSizes) && k != "account" && k != "other" && k != "total") {
                    acctOther += v
                }
            }
            accountInstancesMap[acct]["other"] = acctOther
        }
        
        
        def accountInstances = accountInstancesMap.values().sort { it[params.sort] }
        if (params.order == 'desc') {
            accountInstances = accountInstances.reverse()
        }
        [instanceSizes:instanceSizes, accountInstances: accountInstances, latestReportRun:latestReportRun, totalInstances:totalInstances,
         topSizes:topSizes]

    }

    def show(Long id) {
        def reportRunInstance = ReportRun.get(id)
        if (!reportRunInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportRun.label', default: 'ReportRun'), id])
            redirect(action: "list")
            return
        }

        [reportRunInstance: reportRunInstance]
    }
}
