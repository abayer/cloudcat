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

import org.jclouds.cloudstack.compute.strategy.CloudStackComputeServiceAdapter
import org.jclouds.cloudstack.strategy.BlockUntilJobCompletesAndReturnResult
import org.jclouds.cloudstack.options.ListHostsOptions

import org.apache.commons.logging.LogFactory


class CheckAlertsAndHostsJob {
    private static final log = LogFactory.getLog(this)

    def concurrent = false
    
    def mailService
    
    static triggers = {
        //        cron name: 'importInstancesCron', cronExpression: "0 0 0/3 * * ?"
        simple name: 'testTrigger2', startDelay:1000, repeatInterval:900000, repeatCount:-1
    }

    def group  = "checkAlertsAndHostsGroup"
    
    def execute() {
        if (CloudStackConfig.count()) { 
            def csCfg = CloudStackConfig.where { lastUpdated >= max(lastUpdated) }.find()
            
            def jcloudsConn = new JCloudsConnection(endPointUrl:csCfg.endPointUrl,
                                                    identity:csCfg.apiKey,
                                                    credential:csCfg.secretKey)

            def ctx = jcloudsConn.ctx()

            def csAdapter = ctx.utils().injector().getInstance(CloudStackComputeServiceAdapter.class)
            def blockTask = ctx.utils().injector().getInstance(BlockUntilJobCompletesAndReturnResult.class)
            log.debug("Starting CheckAlertsAndHosts")

            def downHosts = NotifyDownHost.findAllByCurrentState("Down")
            def seenDownHosts = [:]
            
            downHosts.each { dh ->
                ctx.getGlobalContext().getApi().getHostClient().listHosts(ListHostsOptions.Builder.id(dh.hostId)).each { h ->
                    if (h.id == dh.hostId) {
                        seenDownHosts[h.id] = 1
                        if (h.state != "Down") { 
                            dh.currentState = h.state
                            if (h.state == "Up") {
                                dh.upSince = new Date()
                            }
                            dh.save()
                        }
                    }
                }
                // If we didn't find anything about the host, it's been deleted
                if (seenDownHosts[dh.id] == null) {
                    dh.currentState = "Missing"
                    dh.save()
                }
            }
            
            ctx.getGlobalContext().getApi().getHostClient().listHosts(ListHostsOptions.Builder.state("Down")).each { h ->
                // If we haven't seen this host as down in previous run(s)...
                if (seenDownHosts[h.id] == null) {
                    def host
                    if (NotifyDownHost.findByHostId(h.id) != null) {
                        host = NotifyDownHost.findByHostId(h.id)
                    } else {
                        host = new NotifyDownHost()
                    }
                    host.hostName = h.name
                    host.downSince = new Date()
                    host.hostId = h.id
                    host.currentState = h.state

                    host.save()
                }
            }

            if (NotifyDownHost.countByCurrentState("Down") > 0 &&
                csCfg.alertEmailTo != null) {
                def hostMailBody = NotifyDownHost.findAllByCurrentState("Down").collect { dh ->
                    " - ${dh.hostName} (uuid:${dh.hostId}) down since ${dh.downSince}"
                }.join("\n")
                
                mailService.sendMail {
                    from csCfg.alertEmailFrom
                    to csCfg.alertEmailTo.replaceAll(" ", "").split(",")
                    subject "[ALERT] CloudStack Hosts Down as of ${new Date()}"
                    text hostMailBody
                }
                
            }

            def newAlerts = []

            ctx.getGlobalContext().getApi().getAlertClient().listAlerts().each { a ->

                if (NotifySeenAlerts.findByAlertId(a.id) == null) {
                    def seenAlert = new NotifySeenAlerts()
                    seenAlert.alertId = a.id
                    seenAlert.save()
                    newAlerts << a
                }
            }
            
            if (newAlerts.size() > 0 && csCfg.alertEmailTo != null) {
                def alertMailBody = newAlerts.collect { na -> "----\nid: ${na.id}\ntime: ${na.sent}\ntype: ${na.type}\ndescription: ${na.description}\n" }.join("")
                log.warn("Sending a mail for alerts")
                mailService.sendMail {
                    from csCfg.alertEmailFrom
                    to csCfg.alertEmailTo.replaceAll(" ", "").split(",")
                    subject "[ALERT] New CloudStack alerts as of ${new Date()}"
                    text alertMailBody
                }
            }
            log.warn("finished CheckAlertsAndHosts")
        }
    }

}
