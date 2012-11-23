package cloudstack.reporting

import org.springframework.dao.DataIntegrityViolationException

class AlertController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [alertInstanceList: Alert.list(params), alertInstanceTotal: Alert.count()]
    }

    def create() {
        [alertInstance: new Alert(params)]
    }

    def save() {
        def alertInstance = new Alert(params)
        if (!alertInstance.save(flush: true)) {
            render(view: "create", model: [alertInstance: alertInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'alert.label', default: 'Alert'), alertInstance.id])
        redirect(action: "show", id: alertInstance.id)
    }

    def show(Long id) {
        def alertInstance = Alert.get(id)
        if (!alertInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'alert.label', default: 'Alert'), id])
            redirect(action: "list")
            return
        }

        [alertInstance: alertInstance]
    }

    def edit(Long id) {
        def alertInstance = Alert.get(id)
        if (!alertInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'alert.label', default: 'Alert'), id])
            redirect(action: "list")
            return
        }

        [alertInstance: alertInstance]
    }

    def update(Long id, Long version) {
        def alertInstance = Alert.get(id)
        if (!alertInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'alert.label', default: 'Alert'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (alertInstance.version > version) {
                alertInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'alert.label', default: 'Alert')] as Object[],
                          "Another user has updated this Alert while you were editing")
                render(view: "edit", model: [alertInstance: alertInstance])
                return
            }
        }

        alertInstance.properties = params

        if (!alertInstance.save(flush: true)) {
            render(view: "edit", model: [alertInstance: alertInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'alert.label', default: 'Alert'), alertInstance.id])
        redirect(action: "show", id: alertInstance.id)
    }

    def delete(Long id) {
        def alertInstance = Alert.get(id)
        if (!alertInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'alert.label', default: 'Alert'), id])
            redirect(action: "list")
            return
        }

        try {
            alertInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'alert.label', default: 'Alert'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'alert.label', default: 'Alert'), id])
            redirect(action: "show", id: id)
        }
    }
}
