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



import org.junit.*
import grails.test.mixin.*

@TestFor(HostController)
@Mock([Host,ReportRun])
class HostControllerTests {

    def populateValidParams(params) {
        assert params != null
        params.hostId = "xyz"
        params.averageLoad = 1
        params.cpuAllocated = "abc"
        params.cpuNumber = 1
        params.cpuSpeed = 1
        params.cpuUsed = "def"
        params.cpuWithOverProvisioning = 5.0
        params.created = new Date()
        params.disconnected = null
        params.diskSizeAllocated = 1.0
        params.diskSizeTotal = 1.0
        params.hasEnoughCapacity = true
        params.memoryAllocated = 1.0
        params.memoryTotal = 1.0
        params.memoryUsed = 1.0
        params.name = "some-host"
        params.state = "Running"
        
    }

    void testIndex() {
        controller.index()
        assert "/host/list" == response.redirectedUrl
    }

    void testList() {
        println "class: ${controller.class.name}"
        def model = controller.list()

        assert model.hostInstanceList.size() == 0
        assert model.hostInstanceTotal == 0
    }

    void testShow() {
        controller.show(1)

        assert flash.message != null
        assert response.redirectedUrl == '/host/list'

        populateValidParams(params)
        def host = new Host(params)

        assert host.save() != null
        params.id = host.id

        def model = controller.show(params.id)

        assert model.hostInstance == host
    }
}
