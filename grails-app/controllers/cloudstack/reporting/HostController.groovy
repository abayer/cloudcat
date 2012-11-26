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
import grails.converters.JSON
import org.apache.commons.logging.LogFactory

class HostController {
    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def filterPaneService

    static renderBytes(bSize) { 
        def labels = [ ' bytes', 'KB', 'MB', 'GB', 'TB' ]
        def label = labels.find {
            if( bSize < 1024 ) {
                true
            }
            else {
                bSize /= 1024  
                false
            }
        }
        "${new java.text.DecimalFormat( '0.###' ).format( bSize )}$label"
    }
    
    def index() {
        redirect(action: "list", params: params)
    }

    def filterAjax = {
        render filterBase() as JSON
    }
    
    def filterBase = {
        def latestReportRunId = ReportRun.findAllByCompleted(true).collect { it.id }.max()

        params."reportRun.id" = params."reportRun.id" ?: latestReportRunId

        def hostInstanceList = []
        def hostInstanceTotal
        def hostInstanceCounts = [:]
        
        if (params.sort != null && params.sort.equals("instanceCount")) {
            def cntParams = params.clone()
            cntParams.sort = "id"
            cntParams.max = ""
            cntParams.offset = ""
            
            def rawHostInstanceList = filterPaneService.filter(cntParams, Host)
            hostInstanceTotal = filterPaneService.count(cntParams, Host)

            def hostToCount = [:]

            rawHostInstanceList.each { h ->

                hostToCount[h.id] = Instance.withCriteria() { 
                    and {
                        reportRuns {
                            eq("id", params."reportRun.id")
                        }
                        ne("state", "Destroyed")
                        eq("host", h)
                    }
                    
                    projections {
                        countDistinct("id", "instanceCount")
                    }
                }[0]

            }

            def sortedHosts

            if (params.order.equals("desc")) {
                sortedHosts = hostToCount.sort { a, b -> b.value <=> a.value }
            } else {
                sortedHosts = hostToCount.sort { it.value }
            }

            def hostRange
            if (!params.max.equals("")) {
                params.offset = params.offset ?: 0
                hostRange = (sortedHosts.keySet() as List)[params.offset..(params.offset + params.max)]
            } else {
                hostRange = (sortedHosts.keySet() as List)
            }

            hostRange.each { h ->
                def readonlyHost = Host.read(h)
                readonlyHost.instances = []
                readonlyHost.reportRuns = []
                hostInstanceList << readonlyHost
                hostInstanceCounts[h] = hostToCount[h]
            }
        } else {
            hostInstanceList = filterPaneService.filter(params, Host)
            hostInstanceTotal = filterPaneService.count(params, Host)

            hostInstanceList.each { h ->
                hostInstanceCounts[h.id] = Instance.withCriteria() { 
                    and {
                        reportRuns {
                            eq("id", params."reportRun.id")
                        }
                        ne("state", "Destroyed")
                        eq("host", h)
                    }
                    
                    projections {
                        countDistinct("id", "instanceCount")
                    }
                }[0]
                
            }
        }
        
        [hostInstanceList: hostInstanceList.collect { def h = Host.read(it.id); h.instances = []; h.reportRuns = []; h },
         hostInstanceTotal: hostInstanceTotal,
         hostInstanceCounts: hostInstanceCounts,
         filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), 
         params:params,
         latestReportRun: ReportRun.get(params."reportRun.id")]
    }

    def filter = {
        params.max = params.max != null ? params.max as int : 10
        params.max = Math.min(params.max as int ?: 10, 100)
        render(view: 'list', model: filterBase())
    }

    def show(Long id) {
        def latestReportRunId = ReportRun.findAllByCompleted(true).collect { it.id }.max()
        def hostInstance = Host.get(id)
        if (!hostInstance) {
            Flash.message = message(code: 'default.not.found.message', args: [message(code: 'host.label', default: 'Host'), id])
            redirect(action: "list")
            return
        }

        [hostInstance: hostInstance, latestReportRunId: latestReportRunId]
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

}
