package cloudstack.reporting

import org.springframework.dao.DataIntegrityViolationException

class LoggingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = "id"
        params.order = "desc"
        [loggingInstanceList: Logging.list(params), loggingInstanceTotal: Logging.count()]
    }

    def create() {
        [loggingInstance: new Logging(params)]
    }

    def save() {
        def loggingInstance = new Logging(params)
        if (!loggingInstance.save(flush: true)) {
            render(view: "create", model: [loggingInstance: loggingInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'logging.label', default: 'Logging'), loggingInstance.id])
        redirect(action: "show", id: loggingInstance.id)
    }

    def show(Long id) {
        def loggingInstance = Logging.get(id)
        if (!loggingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logging.label', default: 'Logging'), id])
            redirect(action: "list")
            return
        }

        [loggingInstance: loggingInstance]
    }

    def edit(Long id) {
        def loggingInstance = Logging.get(id)
        if (!loggingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logging.label', default: 'Logging'), id])
            redirect(action: "list")
            return
        }

        [loggingInstance: loggingInstance]
    }

    def update(Long id, Long version) {
        def loggingInstance = Logging.get(id)
        if (!loggingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logging.label', default: 'Logging'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (loggingInstance.version > version) {
                loggingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'logging.label', default: 'Logging')] as Object[],
                          "Another user has updated this Logging while you were editing")
                render(view: "edit", model: [loggingInstance: loggingInstance])
                return
            }
        }

        loggingInstance.properties = params

        if (!loggingInstance.save(flush: true)) {
            render(view: "edit", model: [loggingInstance: loggingInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'logging.label', default: 'Logging'), loggingInstance.id])
        redirect(action: "show", id: loggingInstance.id)
    }

    def delete(Long id) {
        def loggingInstance = Logging.get(id)
        if (!loggingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logging.label', default: 'Logging'), id])
            redirect(action: "list")
            return
        }

        try {
            loggingInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'logging.label', default: 'Logging'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'logging.label', default: 'Logging'), id])
            redirect(action: "show", id: id)
        }
    }
}
