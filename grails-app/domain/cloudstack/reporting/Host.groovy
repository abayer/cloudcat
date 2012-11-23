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

class Host {
    String hostId
    String allocationState
    String clusterId
    String clusterName
    String clusterType
    String cpuAllocated
    int cpuNumber
    int cpuSpeed
    String cpuUsed
    float cpuWithOverProvisioning
    Date created
    Date disconnected
    long diskSizeAllocated
    long diskSizeTotal
    String events
    boolean hasEnoughCapacity
    String tags
    String hypervisor
    String ipAddress
    boolean localStorageActive
    String jobId
    String jobStatus
    Date lastPinged
    String managementServerId
    long memoryAllocated
    long memoryTotal
    long memoryUsed
    String name
    long networkKbsRead
    long networkKbsWrite
    String osCategoryId
    String osCategoryName
    String podId
    String podName
    Date removed
    String state
    String type
    String zoneId
    String zoneName
    String capabilities
    int averageLoad
    
    Date lastUpdated

    static hasMany = [instances:Instance, reportRuns:ReportRun]
    static belongsTo = ReportRun

    static constraints = {
        name()
        state()
        cpuNumber()
        cpuAllocated(nullable:true)
        cpuUsed(nullable:true)
        cpuSpeed()
        cpuWithOverProvisioning()
        memoryTotal()
        memoryAllocated()
        hasEnoughCapacity()
        created()
        disconnected(nullable:true)
        osCategoryName(nullable:true)
        removed(nullable:true)
        osCategoryId(nullable:true)
        jobId(nullable:true)
        managementServerId(nullable:true)
        tags(nullable:true)
    }


}
