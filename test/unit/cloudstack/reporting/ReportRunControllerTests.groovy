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

@TestFor(ReportRunController)
@Mock(ReportRun)
class ReportRunControllerTests {

    def populateValidParams(params) {
        assert params != null
    }

    void testIndex() {
        controller.index()
        assert "/reportRun/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reportRunList.size() == 0
        assert model.reportRunTotal == 0
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reportRun/list'

        populateValidParams(params)
        def reportRun = new ReportRun(params)

        assert reportRun.save() != null

        params.id = reportRun.id

        def model = controller.show()

        assert model.reportRunInstance == reportRun
    }
}
