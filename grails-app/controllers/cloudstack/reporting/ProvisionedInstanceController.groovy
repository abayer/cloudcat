package cloudstack.reporting

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import grails.gorm.DetachedCriteria
import grails.converters.JSON
import org.apache.commons.logging.LogFactory

class ProvisionedInstanceController {
    private static final log = LogFactory.getLog(this)

    def filterPaneService

    def springSecurityService
    def provisionInstancesService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def filterAjax = {
        render filterBase() as JSON
    }
    
    def filterBase = {
        [provisionedInstanceInstanceList: filterPaneService.filter(params, ProvisionedInstance),
         provisionedInstanceInstanceTotal: filterPaneService.count(params, ProvisionedInstance),
         filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), 
         params:params]
    }

    def filter() {
        params.max = params.max != null ? params.max as int : 10
        params.max = Math.min(params.max as int ?: 10, 100)
        render(view: 'list', model: filterBase())
    }

    def status(String name, Integer status, String msg) {
        def result = [:]
        def i = ProvisionedInstance.findByInstanceName(name)
        if (!i) {
            result['result'] = 0
            result['message'] = "No instance found for instance name ${name}"
        } else {
            i.provisionStatus = status
            if (errMsg != "") {
                i.errMsg = msg
            }
            i.save(flush:true)

            if (i.hasErrors()) {
                result['result'] = 0
                result['message'] = "Error(s) setting new status for instance ${name}: ${i.errors.allErrors.join(', ')}"
            } else {
                result['result'] = 1
                result['message'] = "Status set for instance ${name}" 
            }
        }

        return result as JSON
    }
    
    def dataTableList() {
        def sortCols = ["id", "instanceName", "provisionStatus"]
        def instanceQuery = new DetachedCriteria(ProvisionedInstance).build {
            and {
                if (params.username != null && !params.username.equals('')) {
                    provisionedInstanceGroup { 
                        eq("username", params.username)
                    }
                }

                if (params.group != null && !params.group.equals('')) { 
                    eq("provisionedInstanceGroup", ProvisionedInstanceGroup.get(params.group))
                }
            }
        } 
        
        def provisionedInstanceList = instanceQuery.list() {
            order(sortCols[params.iSortCol_0 as int], params.sSortDir_0?.equalsIgnoreCase('asc') ? 'asc' : 'desc')
        }
        
        def dataToRender = [:]
        
        dataToRender.aaData = []
        
        dataToRender.iTotalRecords = provisionedInstanceList.size()
        
        dataToRender.iTotalDisplayRecords = dataToRender.iTotalRecords
        
        provisionedInstanceList?.each { i ->
            dataToRender.aaData << [i.id, i.instanceName, ProvisionedInstanceStatus.instanceStatuses[i.provisionStatus]]
        }

        render dataToRender as JSON
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

    def create() {
        [provisionedInstanceInstance: new ProvisionedInstance(params)]
    }

    def save() {
        def provisionedInstanceInstance = new ProvisionedInstance(params)
        if (!provisionedInstanceInstance.save(flush: true)) {
            render(view: "create", model: [provisionedInstanceInstance: provisionedInstanceInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), provisionedInstanceInstance.id])
        redirect(action: "show", id: provisionedInstanceInstance.id)
    }

    def show(Long id) {
        def provisionedInstanceInstance = ProvisionedInstance.get(id)
        if (!provisionedInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action: "list")
            return
        }
        def canModify = getCanModify(provisionedInstanceInstance)

        [provisionedInstanceInstance: provisionedInstanceInstance, canModify:canModify]
    }

    def edit(Long id) {
        def provisionedInstanceInstance = ProvisionedInstance.get(id)
        if (!provisionedInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action: "list")
            return
        }

        [provisionedInstanceInstance: provisionedInstanceInstance]
    }

    def update(Long id, Long version) {
        def provisionedInstanceInstance = ProvisionedInstance.get(id)
        if (!provisionedInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (provisionedInstanceInstance.version > version) {
                provisionedInstanceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance')] as Object[],
                          "Another user has updated this ProvisionedInstance while you were editing")
                render(view: "edit", model: [provisionedInstanceInstance: provisionedInstanceInstance])
                return
            }
        }

        provisionedInstanceInstance.properties = params

        if (!provisionedInstanceInstance.save(flush: true)) {
            render(view: "edit", model: [provisionedInstanceInstance: provisionedInstanceInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), provisionedInstanceInstance.id])
        redirect(action: "show", id: provisionedInstanceInstance.id)
    }

    def delete(Long id) {
        def provisionedInstanceInstance = ProvisionedInstance.get(id)
        if (!provisionedInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action: "list")
            return
        }

        try {
            provisionedInstanceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action: "show", id: id)
        }
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def destroy(Long id) {
        def provisionedInstanceInstance = ProvisionedInstance.get(id)
        def canModify = getCanModify(provisionedInstanceInstance)

        if (!provisionedInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action: "list")
            return
        }

        if (canModify) { 
            if (![3, 4, 5, 6].contains(provisionedInstanceInstance.provisionStatus)) {
                flash.message = message(code: 'provisionedInstance.cant.destroy.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'),
                                                                                                 provisionedInstanceInstance.instanceName])
                redirect(action: "show", id: provisionedInstanceInstance.id)
                return
            }
            
            provisionInstancesService.destroySingleInstance(provisionedInstanceInstance.id)
            new Logging(user:springSecurityService.principal.username,
                        category:LoggingCategory.OPERATION,
                        msg:"Provisioned instance ${provisionedInstanceInstance.id} (${provisionedInstanceInstance.instanceName}) destroyed.").save(flush:true)
            flash.message = message(code: 'provisionedInstance.destroyed.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'),
                                                                                          provisionedInstanceInstance.instanceName])
            redirect(action:"show", id: provisionedInstanceInstance.id)

        } else {
            flash.message = message(code: 'default.cant.modify.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action:"show", id: id)
        }
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def cancel(Long id) {
        def provisionedInstanceInstance = ProvisionedInstance.get(id)
        if (!provisionedInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action: "list")
            return
        }
        def canModify = getCanModify(provisionedInstanceInstance)

        if (canModify) { 
            if (![1, 4].contains(provisionedInstanceInstance.provisionStatus)) {
                flash.message = message(code: 'provisionedInstance.cant.cancel.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'),
                                                                                                     provisionedInstanceInstance.instanceName])
                redirect(action: "show", id: provisionedInstanceInstance.id)
                return
            }
            
            provisionInstancesService.cancelInstances(provisionedInstanceInstance.id)
            new Logging(user:springSecurityService.principal.username,
                        category:LoggingCategory.OPERATION,
                        msg:"Provisioned instance ${provisionedInstanceInstance.id} (${provisionedInstanceInstance.instanceName}) cancelled.").save(flush:true)
            flash.message = message(code: 'provisionedInstance.cancelled.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'),
                                                                                               provisionedInstanceInstance.instanceName])
            redirect(action: "show", id: provisionedInstanceInstance.id)
        } else {
            flash.message = message(code: 'default.cant.modify.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action:"show", id: id)
        }
            
    }
    

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])
    def reprovision(Long id) {
        def provisionedInstanceInstance = ProvisionedInstance.get(id)
        if (!provisionedInstanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action: "list")
            return
        }
        def canModify = getCanModify(provisionedInstanceInstance)

        if (canModify) { 
            if (![2,10].contains(provisionedInstanceInstance.provisionStatus)) {
                flash.message = message(code: 'provisionedInstance.cant.reprovision.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'),
                                                                                                     provisionedInstanceInstance.instanceName])
                redirect(action: "show", id: provisionedInstanceInstance.id)
                return
            }
            
            provisionedInstanceInstance.provisionStatus = 0
            provisionedInstanceInstance.save(flush:true)
            new Logging(user:springSecurityService.principal.username,
                        category:LoggingCategory.OPERATION,
                        msg:"Provisioned instance ${provisionedInstanceInstance.id} (${provisionedInstanceInstance.instanceName}) reprovisioned.").save(flush:true)
            flash.message = message(code: 'provisionedInstance.reprovisioned.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'),
                                                                                               provisionedInstanceInstance.instanceName])
            redirect(action: "show", id: provisionedInstanceInstance.id)
        } else {
            flash.message = message(code: 'default.cant.modify.message', args: [message(code: 'provisionedInstance.label', default: 'ProvisionedInstance'), id])
            redirect(action:"show", id: id)
        }
            
    }
    
    def getCanModify(instance) {
        def isAdmin = SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')
        def isOwner = (springSecurityService.loggedIn ? springSecurityService.principal.username : null)  == instance.provisionedInstanceGroup.username
        def isTeam = (instance.provisionedInstanceGroup.team != null) ? SpringSecurityUtils.ifAllGranted(instance.provisionedInstanceGroup.team.authority) : false
        
        def canModify = isAdmin || isOwner || isTeam

        return canModify
        
    }

}
