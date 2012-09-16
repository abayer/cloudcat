package cloudstack.reporting

import org.springframework.dao.DataIntegrityViolationException

class InstanceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [instanceInstanceList: Instance.list(params), instanceInstanceTotal: Instance.count()]
    }

    def create() {
        [instanceInstance: new Instance(params)]
    }

    def save() {
        def instanceInstance = new Instance(params)
        if (!instanceInstance.save(flush: true)) {
            render(view: "create", model: [instanceInstance: instanceInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'instance.label', default: 'Instance'), instanceInstance.id])
        redirect(action: "show", id: instanceInstance.id)
    }

    def show(Long id) {
        def instanceInstance = Instance.get(id)
        if (!instanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instance.label', default: 'Instance'), id])
            redirect(action: "list")
            return
        }

        [instanceInstance: instanceInstance]
    }

    def edit(Long id) {
        def instanceInstance = Instance.get(id)
        if (!instanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instance.label', default: 'Instance'), id])
            redirect(action: "list")
            return
        }

        [instanceInstance: instanceInstance]
    }

    def update(Long id, Long version) {
        def instanceInstance = Instance.get(id)
        if (!instanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instance.label', default: 'Instance'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (instanceInstance.version > version) {
                instanceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'instance.label', default: 'Instance')] as Object[],
                          "Another user has updated this Instance while you were editing")
                render(view: "edit", model: [instanceInstance: instanceInstance])
                return
            }
        }

        instanceInstance.properties = params

        if (!instanceInstance.save(flush: true)) {
            render(view: "edit", model: [instanceInstance: instanceInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'instance.label', default: 'Instance'), instanceInstance.id])
        redirect(action: "show", id: instanceInstance.id)
    }

    def delete(Long id) {
        def instanceInstance = Instance.get(id)
        if (!instanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'instance.label', default: 'Instance'), id])
            redirect(action: "list")
            return
        }

        try {
            instanceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'instance.label', default: 'Instance'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'instance.label', default: 'Instance'), id])
            redirect(action: "show", id: id)
        }
    }
}
