package cloudstack.reporting

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import grails.gorm.DetachedCriteria
import grails.converters.JSON

class ProvisionedInstanceGroupController {
    private static final log = LogFactory.getLog(this)

    def filterPaneService
    
    def springSecurityService
    def provisionInstancesService
    
    static allowedMethods = [save: "POST", update: "POST", delete: "POST", destroy: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def ajaxStatus(Long id) {
        def provisionedInstanceGroupInstance = ProvisionedInstanceGroup.get(id)
        if (!provisionedInstanceGroupInstance) {
            render(JSON.parse("{provisionStatus: 'No group found'}"))
        } else {
            render(JSON.parse("{provisionStatus: ${ProvisionedInstanceStatus.groupStatuses[provisionedInstanceGroupInstance.provisionStatus]}}"))
        }
    }

    def filterAjax = {
        render filterBase() as JSON
    }
    
    def filterBase = {
        def qParams = params.clone()

        if (params['filter.provisionStatus'] != null && params['filter.provisionStatus'] != "") {
            qParams.filter.provisionStatus = "${ProvisionedInstanceStatus.groupStatuses.indexOf(params['filter.provisionStatus'])}"
        }
        [provisionedInstanceGroupInstanceList: filterPaneService.filter(qParams, ProvisionedInstanceGroup),
         provisionedInstanceGroupInstanceTotal: filterPaneService.count(qParams, ProvisionedInstanceGroup),
         filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), 
         params:params]
    }

    def filter() {
        log.warn("params: ${params}")
        params.max = params.max != null ? params.max as int : 10
        params.max = Math.min(params.max as int ?: 10, 100)
        render(view: 'list', model: filterBase())
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def newParams = [:]
        params.each { k, v ->
            if (k.contains("filter_")) {
                k = k.replaceAll("_", ".")
            }
            newParams[k] = v
        }
        redirect(action:'filter', params:newParams)

    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def create() {
        log.warn("In create")
        def provGroup = new ProvisionedInstanceGroup(params)
        
        [provisionedInstanceGroupInstance: provGroup]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def save() {
        log.warn("In save params")
        if (params.templateChooser == "2") {
            params.template.id = Template.findByName(params.templateText).id
            params."template.id" = Template.findByName(params.templateText).id
        }
        def provGroup = new ProvisionedInstanceGroup(params)
        if (!provGroup.save(flush: true)) {
            render(view: "create", model: [provisionedInstanceGroupInstance: provGroup, templateChooser:params.templateChooser as int])
            return
        }

        log.warn("About to try provisioning")
        provisionInstancesService.provisionInstances(provGroup.id)
        new Logging(user:springSecurityService.principal.username,
                    category:LoggingCategory.OPERATION,
                    msg:"Provisioned instance group ${provGroup.id} (${provGroup.shortName}) created.").save(flush:true)
        def canModify = getCanModify(provGroup)

        flash.message = message(code: 'default.created.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), provGroup.id])
        redirect(action: "show", id: provGroup.id, canModify:canModify)
    }

    def show(Long id) {
        def provisionedInstanceGroupInstance = ProvisionedInstanceGroup.get(id)
        if (!provisionedInstanceGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
            redirect(action: "list")
            return
        }

        def canModify = getCanModify(provisionedInstanceGroupInstance)
        
        [provisionedInstanceGroupInstance: provisionedInstanceGroupInstance, canModify:canModify]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def edit(Long id) {
        def provisionedInstanceGroupInstance = ProvisionedInstanceGroup.get(id)
        if (!provisionedInstanceGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
            redirect(action: "list")
            return
        }

        def canModify = getCanModify(provisionedInstanceGroupInstance)

        [provisionedInstanceGroupInstance: provisionedInstanceGroupInstance, canModify:canModify]
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def update(Long id, Long version) {
        def provisionedInstanceGroupInstance = ProvisionedInstanceGroup.get(id)
        if (params.templateText != null) {
            params.template.id = Template.findByName(params.templateText).id
        }
        def canModify = getCanModify(provisionedInstanceGroupInstance)

        if (!provisionedInstanceGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
            redirect(action: "list")
            return
        }


        if (canModify) { 
            if (version != null) {
                if (provisionedInstanceGroupInstance.version > version) {
                    provisionedInstanceGroupInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                                                                        [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup')] as Object[],
                                                                        "Another user has updated this ProvisionedInstanceGroup while you were editing")
                    render(view: "edit", model: [provisionedInstanceGroupInstance: provisionedInstanceGroupInstance, canModify:canModify])
                    return
                }
            }
            
            provisionedInstanceGroupInstance.properties = params
            
            if (!provisionedInstanceGroupInstance.save(flush: true)) {
                render(view: "edit", model: [provisionedInstanceGroupInstance: provisionedInstanceGroupInstance, canModify:canModify])
                return
            }
            
            new Logging(user:springSecurityService.principal.username,
                        category:LoggingCategory.OPERATION,
                        msg:"Provisioned instance group ${provisionedInstanceGroupInstance.id} (${provisionedInstanceGroupInstance.updated} created.").save(flush:true)
            flash.message = message(code: 'default.updated.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), provisionedInstanceGroupInstance.id])
            redirect(action:"show", id: provisionedInstanceGroupInstance.id)
        } else {
            flash.message = message(code: 'default.cant.modify.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
            redirect(action:"show", id: id)
        }
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def destroy(Long id) {
        def provisionedInstanceGroupInstance = ProvisionedInstanceGroup.get(id)
        def canModify = getCanModify(provisionedInstanceGroupInstance)

        if (!provisionedInstanceGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
            redirect(action: "list")
            return
        }

        if (canModify) { 
            if (![2, 3, 5, 6, 9].contains(provisionedInstanceGroupInstance.provisionStatus)) {
                flash.message = message(code: 'provisionedInstanceGroup.cant.destroy.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'),
                                                                                     provisionedInstanceGroupInstance.shortName])
                redirect(action: "show", id: provisionedInstanceGroupInstance.id)
                return
            }
            
            provisionInstancesService.destroyInstances(provisionedInstanceGroupInstance.id)
            new Logging(user:springSecurityService.principal.username,
                        category:LoggingCategory.OPERATION,
                        msg:"Provisioned instance group ${provisionedInstanceGroupInstance.id} (${provisionedInstanceGroupInstance.shortName}) destroyed.").save(flush:true)
            flash.message = message(code: 'provisionedInstanceGroup.destroyed.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'),
                                                                              provisionedInstanceGroupInstance.shortName])
            redirect(action:"show", id: provisionedInstanceGroupInstance.id)

        } else {
            flash.message = message(code: 'default.cant.modify.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
            redirect(action:"show", id: id)
        }
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def cancel(Long id) {
        def provisionedInstanceGroupInstance = ProvisionedInstanceGroup.get(id)
        if (!provisionedInstanceGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
            redirect(action: "list")
            return
        }
        def canModify = getCanModify(provisionedInstanceGroupInstance)

        if (canModify) { 
            if (![1, 4].contains(provisionedInstanceGroupInstance.provisionStatus)) {
                flash.message = message(code: 'provisionedInstanceGroup.cant.cancel.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'),
                                                                                                     provisionedInstanceGroupInstance.shortName])
                redirect(action: "show", id: provisionedInstanceGroupInstance.id)
                return
            }
            
            provisionInstancesService.cancelInstances(provisionedInstanceGroupInstance.id)
            new Logging(user:springSecurityService.principal.username,
                        category:LoggingCategory.OPERATION,
                        msg:"Provisioned instance group ${provisionedInstanceGroupInstance.id} (${provisionedInstanceGroupInstance.shortName}) cancelled.").save(flush:true)
            flash.message = message(code: 'provisionedInstanceGroup.cancelled.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'),
                                                                                               provisionedInstanceGroupInstance.shortName])
            redirect(action: "show", id: provisionedInstanceGroupInstance.id)
        } else {
            flash.message = message(code: 'default.cant.modify.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
            redirect(action:"show", id: id)
        }
            
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def delete(Long id) {
        def provisionedInstanceGroupInstance = ProvisionedInstanceGroup.get(id)
        if (!provisionedInstanceGroupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
            redirect(action: "list")
            return
        }

        def canModify = getCanModify(provisionedInstanceGroupInstance)

        if (canModify) { 
            try {
                provisionedInstanceGroupInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
                redirect(action: "list")
            }
            catch (DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
                redirect(action: "show", id: id)
            }
        } else {
            flash.message = message(code: 'default.cant.modify.message', args: [message(code: 'provisionedInstanceGroup.label', default: 'ProvisionedInstanceGroup'), id])
            redirect(action:"show", id: id)
        }
    }


    def getCanModify(groupInstance) {
        def isAdmin = SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')
        def isOwner = (springSecurityService.loggedIn ? springSecurityService.principal.username : null)  == groupInstance.username
        def isTeam = (groupInstance.team != null) ? SpringSecurityUtils.ifAllGranted(groupInstance.team.authority) : false
        
        def canModify = isAdmin || isOwner || isTeam

        return canModify
        
    }
}
