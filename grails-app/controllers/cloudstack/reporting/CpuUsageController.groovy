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

class CpuUsageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def sortTok = params.sort ?: "dateCreated"
        def orderTok = params.order ?: "desc"
        def cpuUsageInstanceList
        def cpuUsageInstanceCount
        if (params.instanceId) {
            cpuUsageInstanceList = CpuUsage.executeQuery("select c from CpuUsage as c where c.instance.id = :id order by c.${sortTok} ${orderTok}",
                                                         [id: params.instanceId.toLong()],
                                                         [max: params.max ?: 0, offset: params.offset ?: 0])
            cpuUsageInstanceCount = CpuUsage.executeQuery("select count(c) from CpuUsage as c where c.instance.id = :id",
                                                          [id: params.instanceId.toLong()])
        } else {
            cpuUsageInstanceList = CpuUsage.list(params)
            cpuUsageInstanceCount = CpuUsage.count()
        }
        [cpuUsageInstanceList: cpuUsageInstanceList, cpuUsageInstanceTotal: cpuUsageInstanceCount, cpuUsageInstanceId: params.instanceId]
    }

    def show(Long id) {
        def cpuUsageInstance = CpuUsage.get(id)
        if (!cpuUsageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cpuUsage.label', default: 'CpuUsage'), id])
            redirect(action: "list")
            return
        }

        [cpuUsageInstance: cpuUsageInstance]
    }
}
