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

import org.jclouds.cloudstack.options.ListTemplatesOptions
import org.jclouds.cloudstack.domain.VirtualMachine
import org.jclouds.cloudstack.domain.TemplateFilter

import cloudstack.reporting.Instance
import cloudstack.reporting.ReportRun

import org.apache.commons.logging.LogFactory


class ImportInstancesJob {
    private static final log = LogFactory.getLog(this)

    def concurrent = false
    
    static triggers = {
        //        cron name: 'importInstancesCron', cronExpression: "0 0 0/3 * * ?"
        simple name: 'testTrigger', startDelay:300000, repeatInterval:3600000, repeatCount:-1
    }

    def group  = "importInstancesGroup"
    
    def execute() {
        if (CloudStackConfig.count()) { 
            def csCfg = CloudStackConfig.where { lastUpdated >= max(lastUpdated) }.find()
            
            def jcloudsConn = new JCloudsConnection(endPointUrl:csCfg.endPointUrl,
                                                    identity:csCfg.apiKey,
                                                    credential:csCfg.secretKey)

            def ctx = jcloudsConn.ctx()

            log.warn("Starting reporting")

            def serviceOfferingsThisRun = []
            ctx.getGlobalContext().getApi().getOfferingClient().listServiceOfferings().each { s ->
                def serviceOffering
                if (ServiceOffering.findByOfferingId(s.id) != null) {
                    serviceOffering = ServiceOffering.findByOfferingId(s.id)
                } else {
                    serviceOffering = new ServiceOffering()
                }

                serviceOffering.offeringId = s.id
                serviceOffering.name = s.name
                serviceOffering.displayText = s.displayText
                serviceOffering.created = s.created
                serviceOffering.domain = s.domain
                serviceOffering.domainId = s.domainId
                serviceOffering.cpuNumber = s.cpuNumber
                serviceOffering.cpuSpeed = s.cpuSpeed
                serviceOffering.memory = s.memory
                serviceOffering.haSupport = s.haSupport
                serviceOffering.storageType = s.storageType.toString()
                serviceOffering.defaultUse = s.defaultUse
                serviceOffering.systemOffering = s.systemOffering
                serviceOffering.cpuUseLimited = s.cpuUseLimited
                serviceOffering.networkRate = s.networkRate
                serviceOffering.systemVmType = s.systemVmType
                serviceOffering.tags = s.tags.join(", ")

                serviceOffering.stillActive = true
                
                if (serviceOffering.validate()) {
                    serviceOffering.save()
                    serviceOfferingsThisRun << s.id
                } else {
                    serviceOffering.errors.allErrors.each {
                        log.warn("serviceOffering ${s.id} error: ${it}")
                    }
                }
            }

            ServiceOffering.findAllByStillActive(true).each { s ->
                if (!serviceOfferingsThisRun.contains(s.offeringId)) { 
                    s.stillActive = false
                    s.save()
                }
            }
            
            
            def reportRun = new ReportRun()
            reportRun.completed = false
            
            def hosts = [:]
            
            ctx.getGlobalContext().getApi().getHostClient().listHosts().each { h ->
                if (h.capabilities != null && h.capabilities =~ /hvm/) { 
                    def host
                    if (Host.findByHostId(h.id) != null) {
                        host = Host.findByHostId(h.id)
                    } else {
                        host = new Host()
                    }
                    
                    host.hostId = h.id
                    host.averageLoad = h.averageLoad
                    host.cpuAllocated = h.cpuAllocated
                    host.cpuNumber = h.cpuNumber
                    host.cpuSpeed = h.cpuSpeed
                    host.cpuUsed = h.cpuUsed
                    host.cpuWithOverProvisioning = h.cpuWithOverProvisioning
                    host.created = h.created
                    host.disconnected = h.disconnected
                    host.diskSizeAllocated = h.diskSizeAllocated
                    host.diskSizeTotal = h.diskSizeTotal
                    host.hasEnoughCapacity = h.hasEnoughCapacity
                    host.memoryAllocated = h.memoryAllocated
                    host.memoryTotal = h.memoryTotal
                    host.memoryUsed = h.memoryUsed
                    host.name = h.name
                    host.capabilities = h.capabilities
                    host.allocationState = h.allocationState.toString()
                    host.clusterId = h.clusterId
                    host.clusterName = h.clusterName
                    host.clusterType = h.clusterType.toString()
                    host.events = h.events
                    host.tags = h.getTags().join(",")
                    host.hypervisor = h.hypervisor
                    host.ipAddress = h.ipAddress
                    host.localStorageActive = h.localStorageActive
                    host.jobId = h.jobId
                    host.jobStatus = h.jobStatus.toString()
                    host.lastPinged = h.lastPinged
                    host.managementServerId = h.managementServerId
                    host.networkKbsRead = h.networkKbsRead
                    host.networkKbsWrite = h.networkKbsWrite
                    host.osCategoryId = h.osCategoryId
                    host.osCategoryName = h.osCategoryName
                    host.podId = h.podId
                    host.podName = h.podName
                    host.removed = h.removed
                    host.type = h.type.toString()
                    host.zoneId = h.zoneId
                    host.zoneName = h.zoneName
                    host.state = h.state.toString()
                    
                    if (host.validate()) {
                        reportRun.addToHosts(host)
                        hosts[host.hostId] = host
                    } else {
                        host.errors.allErrors.each {
                            log.warn("host ${host.hostId} error: ${it}")
                        }
                    }
                }
                
            }
            reportRun.save()

            def templatesThisRun = []
            
            ctx.getGlobalContext().getApi().getTemplateClient().listTemplates(ListTemplatesOptions.Builder.filter(TemplateFilter.ALL)).each { t ->
                def template

                if (Template.findByTemplateId(t.id) != null) {
                    template = Template.findByTemplateId(t.id)
                } else {
                    template = new Template()
                }

                template.templateId = t.id
                template.displayText = t.displayText
                template.domain = t.domain
                template.domainId = t.domainId
                template.account = t.account
                template.accountId = t.accountId
                template.zone = t.zone
                template.zoneId = t.zoneId
                template.OSType = t.OSType
                template.OSTypeId = t.OSTypeId
                template.name = t.name
                template.type = t.type.toString()
                template.status = t.status.toString()
                template.format = t.format.toString()
                template.hypervisor = t.hypervisor
                template.size = t.size
                template.created = t.created
                template.removed = t.removed
                template.crossZones = t.crossZones
                template.bootable = t.bootable
                template.extractable = t.extractable
                template.featured = t.featured
                template.ispublic = t.ispublic
                template.ready = t.ready
                template.passwordEnabled = t.passwordEnabled
                template.jobId = t.jobId
                template.jobStatus = t.jobStatus
                template.hostId = t.hostId
                template.hostName = t.hostName
                template.sourceTemplateId = t.sourceTemplateId
                template.templateTag = t.templateTag
                template.stillActive = true

                templatesThisRun << t.id
                
                if (template.validate()) {
                    template.save()
                } else {
                    template.errors.allErrors.each {
                        log.warn("template ${template.templateId} error: ${it}")
                    }
                }
            }


            Template.findAllByStillActive(true).each { t ->
                if (!templatesThisRun.contains(t.templateId)) { 
                    t.stillActive = false
                    t.save()
                }
            }

            ctx.getGlobalContext().getApi().getVirtualMachineClient().listVirtualMachines().each { vm ->
                def isNew = false
                def inst
                if (Instance.findByInstanceId(vm.id) != null) {
                    inst = Instance.findByInstanceId(vm.id)
                } else {
                    inst = new Instance()
                    isNew = true
                }
                
                inst.instanceId = vm.id
                inst.state = vm.state.toString()
                def cpuUsageObj = new CpuUsage()
                cpuUsageObj.cpuUsage = vm.cpuUsed
                if (cpuUsageObj.validate()) { 
                    inst.addToCpuUsages(cpuUsageObj)
                } else {
                    cpuUsageObj.errors.allErrors.each {
                        log.warn("cpuUsage error: ${it}")
                    }
                }
                inst.publicIPs = vm.nics.collect { n -> n.getIPAddress() }.join('|')
                inst.account = vm.account
                inst.cpuCount = vm.cpuCount
                inst.cpuSpeed = vm.cpuSpeed
                inst.cpuUsed = vm.cpuUsed
                inst.displayName = vm.displayName
                inst.created = vm.created
                inst.domain = vm.domain
                inst.domainId = vm.domainId
                inst.usesVirtualNetwork = vm.usesVirtualNetwork
                inst.groupName = vm.group
                inst.groupId = vm.groupId
                inst.guestOSId = vm.guestOSId
                inst.HAEnabled = vm.HAEnabled
                inst.hostname = vm.hostname
                inst.IPAddress = vm.IPAddress
                inst.ISODisplayText = vm.ISODisplayText
                inst.ISOId = vm.ISOId
                inst.ISOName = vm.ISOName
                inst.jobId = vm.jobId
                inst.jobStatus = vm.jobStatus
                inst.memory = vm.memory
                inst.name = vm.name
                inst.networkKbsRead = vm.networkKbsRead
                inst.networkKbsWrite = vm.networkKbsWrite
                inst.instancePassword = vm.password
                inst.passwordEnabled = vm.passwordEnabled
                inst.publicIPId = vm.publicIPId
                inst.rootDeviceId = vm.rootDeviceId
                inst.rootDeviceType = vm.rootDeviceType
                inst.serviceOfferingCsId = vm.serviceOfferingId
                inst.serviceOfferingName = vm.serviceOfferingName
                inst.templateDisplayText = vm.templateDisplayText
                inst.csTemplateId = vm.templateId
                inst.templateName = vm.templateName
                inst.zoneId = vm.zoneId
                inst.zoneName = vm.zoneName
                inst.hypervisor = vm.hypervisor

                if (inst.validate()) {
                    if (vm.hostId != null && Host.findByHostId(vm.hostId) != null) {
                        if (inst.host != null && inst.host.hostId != vm.hostId) {
                            Host.findByHostId(vm.hostId).removeFromInstances(inst)
                        }
                        if (inst.host == null) { 
                            Host.findByHostId(vm.hostId).addToInstances(inst)
                        }
                    }
                    if (isNew) {
                        if (vm.serviceOfferingId != null
                           && ServiceOffering.findByOfferingId(vm.serviceOfferingId) != null) {
                            ServiceOffering.findByOfferingId(vm.serviceOfferingId).addToInstances(inst).save()
                        }
                        if (vm.templateId != null && Template.findByTemplateId(vm.templateId) != null) {
                            Template.findByTemplateId(vm.templateId).addToInstances(inst).save()
                        }
                    }

                    reportRun.addToInstances(inst)
                } else {
                    inst.errors.allErrors.each {
                        log.warn("inst ${inst.instanceId} error: ${it}")
                    }
                }
            }
            reportRun.completed = true
            reportRun.save()

            
            log.warn("finished")
        }
    }
}
