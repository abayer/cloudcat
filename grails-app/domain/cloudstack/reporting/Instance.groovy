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

class Instance {
    String instanceId
    String account
    long cpuCount
    long cpuSpeed
    String cpuUsed
    String displayName
    Date created
    String domain
    String domainId
    boolean usesVirtualNetwork
    String groupName
    String groupId
    String guestOSId
    boolean HAEnabled
    String hostname
    String IPAddress
    String ISODisplayText
    String ISOId
    String ISOName
    String jobId
    Integer jobStatus
    long memory
    String name
    Long networkKbsRead
    Long networkKbsWrite
    String instancePassword
    boolean passwordEnabled
    String publicIPs
    String publicIPId
    String rootDeviceId
    String rootDeviceType
    String serviceOfferingCsId
    String serviceOfferingName
    String state
    String templateDisplayText
    String csTemplateId
    String templateName
    String zoneId
    String zoneName
    String hypervisor


    Date lastUpdated

    Host host
    Template template
    ServiceOffering serviceOffering
    
    static hasMany = [reportRuns:ReportRun,cpuUsages:CpuUsage]

    static belongsTo = [ReportRun, Host, Template, ServiceOffering]

    static constraints = {
        name()
        state()
        account()
        serviceOfferingName()
        memory()
        cpuCount()
        created()
        cpuUsed(nullable:true)
        publicIPs()
        templateName()
        csTemplateId()
        serviceOffering(nullable:true)
        host(nullable:true)
        reportRuns(nullable:true)
        cpuUsages(nullable:true)
        publicIPId(nullable:true)
        jobStatus(nullable:true)
        jobId(nullable:true)
        instancePassword(nullable:true)
        groupName(nullable:true)
        groupId(nullable:true)
        ISOName(nullable:true)
        ISOId(nullable:true)
        ISODisplayText(nullable:true)
        IPAddress(nullable:true)
        hostname(nullable:true)
        networkKbsWrite(nullable:true)
        networkKbsRead(nullable:true)
        template(nullable:true)
    }
}
