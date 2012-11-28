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
import grails.gorm.DetachedCriteria
import grails.converters.JSON
import org.apache.commons.logging.LogFactory

class InstanceController {
    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def filterPaneService

    def index() {
        redirect(action: "list", params: params)
    }

    def filterAjax = {
        render filterBase() as JSON
    }
    
    def filterBase = {
        def latestReportRunId = ReportRun.findAllByCompleted(true).collect { it.id }.max()
        
        def queryReportRun = ReportRun.get(params.reportRun ? params.reportRun.toLong() : latestReportRunId)
        
        def qParams = params.clone()

        if (params['filter.op.lastUpdated'] == null || params['filter.op.lastUpdated'] == "") {
            qParams.filter = [:]
            qParams.filter.op = [:]
            qParams.filter.Instance = [:]
            
            qParams.filter.lastUpdated = queryReportRun.dateCreated
            qParams.filter.op.lastUpdated = "GreaterThanEquals" 
            qParams.filter.Instance.lastUpdated_isDayPrecision = "n"
        }
        
        [instanceInstanceList: filterPaneService.filter(qParams, Instance).collect { def i = Instance.read(it.id); i.reportRuns = []; i.cpuUsages = []; i},
         instanceInstanceTotal: filterPaneService.count(qParams, Instance),
         filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), 
         params:params]
    }

    def filter() {
        params.max = params.max != null ? params.max as int : 10
        params.max = Math.min(params.max as int ?: 10, 100)
        render(view: 'list', model: filterBase())
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def newParams = [:]
        params.each { k, v ->
            if (k.contains("filter_")) {
                k = k.replaceAll("_", ".")
            }
            newParams[k] = v
        }
        redirect(action:'filter', params:newParams)
    }

    def find(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def sortTok = params.sort ?: "name"
        def orderTok = params.order ?: "asc"
        def latestReportRunId = ReportRun.findAllByCompleted(true).collect { it.id }.max()
                
        def queryReportRun = ReportRun.get(params.reportRun ? params.reportRun.toLong(): latestReportRunId)

        def queryTemplate

        if (params.templateId) {
            queryTemplate = Template.get(params.templateId)
        }

        def instanceQuery = new DetachedCriteria(Instance).build {
            and {
                if (params.templateId != null && !params.templateId.equals('')) {
                    eq("csTemplateId", queryTemplate.templateId)
                }

                if (params.account != null && !params.account.equals('')) { 
                    eq("account", params.account)
                }

                ge("lastUpdated", queryReportRun.dateCreated)
                ne("state", "Destroyed")
            }
        } 

        def instanceInstanceList = instanceQuery.list(max:params.max ?: 0,
                                                      offset:params.offset ?: 0) { 
            order(sortTok, orderTok)
        }
        
        def instanceInstanceTotal = instanceQuery.list().size()

        [account: params.account, reportRun: params.reportRun, templateId: params.templateId,
         templateName: queryTemplate?.name ?: "(none)",
         instanceInstanceList: instanceInstanceList,
         instanceInstanceTotal: instanceInstanceTotal]
    }

    def show(Long id) {
        def instanceInstance = Instance.get(id)
        if (!instanceInstance) {
            Flash.message = message(code: 'default.not.found.message', args: [message(code: 'instance.label', default: 'Instance'), id])
            redirect(action: "list")
            return
        }

        def cpuUsagesRaw = CpuUsage.findAllByInstance(instanceInstance, [sort: 'dateCreated', order: 'desc', max: '120'])
        def cpuUsages = cpuUsagesRaw.collect { c ->
            ["${hoursAgo(c.dateCreated)} ago", c.cpuUsage]
        }.reverse()

        [instanceInstance: instanceInstance, cpuUsages: cpuUsages]
    }

    def hoursAgo(d) {
        use (groovy.time.TimeCategory) {
            def now = new Date()
            def duration = now - d

            if (duration.days > 0) {
                return "${(duration.days * 24) + duration.hours} hours"
            } else if (duration.hours > 1) {
                return "${duration.hours} hours"
            } else if (duration.hours > 0) {
                return "${duration.hours} hour"
            } else if (duration.minutes > 1) {
                return "${duration.minutes} mins"
            } else { 
                return "${duration.minutes} min"
            }
        }
    }
}
