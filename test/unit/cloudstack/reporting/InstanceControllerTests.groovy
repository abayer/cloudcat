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

@TestFor(InstanceController)
@Mock(Instance)
class InstanceControllerTests {

    def populateValidParams(params) {
        assert params != null
        
        params.instanceId = "abc"
        params.account = "def"
        params.name = "ghi" 
        params.state = "Running"
        params.cpuCount = 1.0
        params.created = new Date()
        params.publicIPs = "1.2.3.4"
        params.memory = 1.0
        params.templateId = "jkl"
        params.templateName = "mno"
        params.serviceOfferingName = "pqr"
        params.rootDeviceId = "stu"
    }

    void testIndex() {
        controller.index()
        assert "/instance/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list(10)

        assert model.instanceInstanceList.size() == 0
        assert model.instanceInstanceTotal == 0
    }

    void testShow() {
        controller.show(1)

        assert flash.message != null
        assert response.redirectedUrl == '/instance/list'

        populateValidParams(params)
        def instance = new Instance(params)

        assert instance.save() != null
        params.id = instance.id

        def model = controller.show(params.id)

        assert model.instanceInstance == instance
    }
}
