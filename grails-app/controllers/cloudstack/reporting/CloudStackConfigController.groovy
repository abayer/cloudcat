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
import grails.plugins.springsecurity.Secured

class CloudStackConfigController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [cloudStackConfigInstanceList: CloudStackConfig.list(params), cloudStackConfigInstanceTotal: CloudStackConfig.count()]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def create() {
        [cloudStackConfigInstance: new CloudStackConfig(params)]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def save() {
        def cloudStackConfigInstance = new CloudStackConfig(params)
        if (!cloudStackConfigInstance.save(flush: true)) {
            render(view: "create", model: [cloudStackConfigInstance: cloudStackConfigInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cloudStackConfig.label', default: 'CloudStackConfig'), cloudStackConfigInstance.id])
        redirect(action: "show", id: cloudStackConfigInstance.id)
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def show(Long id) {
        def cloudStackConfigInstance = CloudStackConfig.get(id)
        if (!cloudStackConfigInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cloudStackConfig.label', default: 'CloudStackConfig'), id])
            redirect(action: "list")
            return
        }

        [cloudStackConfigInstance: cloudStackConfigInstance]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def edit(Long id) {
        def cloudStackConfigInstance = CloudStackConfig.get(id)
        if (!cloudStackConfigInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cloudStackConfig.label', default: 'CloudStackConfig'), id])
            redirect(action: "list")
            return
        }

        [cloudStackConfigInstance: cloudStackConfigInstance]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def update(Long id, Long version) {
        def cloudStackConfigInstance = CloudStackConfig.get(id)
        if (!cloudStackConfigInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cloudStackConfig.label', default: 'CloudStackConfig'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cloudStackConfigInstance.version > version) {
                cloudStackConfigInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cloudStackConfig.label', default: 'CloudStackConfig')] as Object[],
                          "Another user has updated this CloudStackConfig while you were editing")
                render(view: "edit", model: [cloudStackConfigInstance: cloudStackConfigInstance])
                return
            }
        }

        cloudStackConfigInstance.properties = params

        if (!cloudStackConfigInstance.save(flush: true)) {
            render(view: "edit", model: [cloudStackConfigInstance: cloudStackConfigInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cloudStackConfig.label', default: 'CloudStackConfig'), cloudStackConfigInstance.id])
        redirect(action: "show", id: cloudStackConfigInstance.id)
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def delete(Long id) {
        def cloudStackConfigInstance = CloudStackConfig.get(id)
        if (!cloudStackConfigInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cloudStackConfig.label', default: 'CloudStackConfig'), id])
            redirect(action: "list")
            return
        }

        try {
            cloudStackConfigInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cloudStackConfig.label', default: 'CloudStackConfig'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cloudStackConfig.label', default: 'CloudStackConfig'), id])
            redirect(action: "show", id: id)
        }
    }
}
