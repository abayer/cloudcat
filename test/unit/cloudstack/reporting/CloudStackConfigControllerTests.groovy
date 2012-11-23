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

@TestFor(CloudStackConfigController)
@Mock(CloudStackConfig)
class CloudStackConfigControllerTests {

    def populateValidParams(params) {
        assert params != null
        params.endPointUrl = 'http://foo.bar.com:8348/client/api'
        params.apiKey = 'abcdefgh'
        params.secretKey = 'ijklmnop'
    }

    void testIndex() {
        controller.index()
        assert "/cloudStackConfig/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cloudStackConfigInstanceList.size() == 0
        assert model.cloudStackConfigInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cloudStackConfigInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cloudStackConfigInstance != null
        assert view == '/cloudStackConfig/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cloudStackConfig/show/1'
        assert controller.flash.message != null
        assert CloudStackConfig.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cloudStackConfig/list'

        populateValidParams(params)
        def cloudStackConfig = new CloudStackConfig(params)

        assert cloudStackConfig.save() != null

        params.id = cloudStackConfig.id

        def model = controller.show()

        assert model.cloudStackConfigInstance == cloudStackConfig
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cloudStackConfig/list'

        populateValidParams(params)
        def cloudStackConfig = new CloudStackConfig(params)

        assert cloudStackConfig.save() != null

        params.id = cloudStackConfig.id

        def model = controller.edit()

        assert model.cloudStackConfigInstance == cloudStackConfig
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cloudStackConfig/list'

        response.reset()

        populateValidParams(params)
        def cloudStackConfig = new CloudStackConfig(params)

        assert cloudStackConfig.save() != null

        // test invalid parameters in update
        params.id = cloudStackConfig.id
        params.endPointUrl = ''

        controller.update()

        assert view == "/cloudStackConfig/edit"
        assert model.cloudStackConfigInstance != null

        cloudStackConfig.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cloudStackConfig/show/$cloudStackConfig.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cloudStackConfig.clearErrors()

        populateValidParams(params)
        params.id = cloudStackConfig.id
        params.version = -1
        controller.update()

        assert view == "/cloudStackConfig/edit"
        assert model.cloudStackConfigInstance != null
        assert model.cloudStackConfigInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cloudStackConfig/list'

        response.reset()

        populateValidParams(params)
        def cloudStackConfig = new CloudStackConfig(params)

        assert cloudStackConfig.save() != null
        assert CloudStackConfig.count() == 1

        params.id = cloudStackConfig.id

        controller.delete()

        assert CloudStackConfig.count() == 0
        assert CloudStackConfig.get(cloudStackConfig.id) == null
        assert response.redirectedUrl == '/cloudStackConfig/list'
    }
}
