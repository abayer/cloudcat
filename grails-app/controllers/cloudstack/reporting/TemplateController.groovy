package cloudstack.reporting

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory
import org.hibernate.criterion.CriteriaSpecification
import org.hibernate.type.Type
import org.hibernate.criterion.Projections
import org.hibernate.Hibernate
import org.hibernate.criterion.Restrictions
import grails.converters.JSON

class TemplateController {
    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }


    def validTemplateAJAX() {
        def found
        if (params.templateText == null || Template.findByName(params.templateText) != null) {
            found = "true"
        } else {
            found = "false"
        }
        response.setContentType("text/json;charset=UTF-8");

        render found  
    }
    
    def templateListAJAX() {
        def templates = Template.findAllByIspublicAndReadyAndNameLike(true, true, "%${params.term}%")

        render templates.collect { it.name } as JSON
    }
     
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def sortTok = params.sort ?: "name"
        def orderTok = params.order ?: "asc"

        def latestReportRun = ReportRun.withCriteria {
            geAll("dateCreated", {
                      projections {
                          property "dateCreated"
                      }
                      eq("completed", true)
                  })
        }[0]

        def templateInstanceTotal = Template.count()
        log.warn("latestReportRun.dateCreated: ${latestReportRun.dateCreated}")

        def rawTemplateList = Template.withCriteria() { 
            getInstance().createAlias("instances", "i", CriteriaSpecification.LEFT_JOIN, Restrictions.and(
                                          Restrictions.ge("i.lastUpdated", latestReportRun.dateCreated),
                                          Restrictions.ne("i.state", "Destroyed")))
            projections {
                groupProperty("id")
                property("name", "name")
                property("templateId", "templateId")
                property("stillActive", "stillActive")
                property("displayText", "displayText")
                countDistinct("i.id", "instanceCount")
            }
            order(sortTok, orderTok)
            maxResults(params.max ?: 0)
            firstResult(params.offset ? params.offset.toInteger() : 0 )
        }

        def templateInstanceList = rawTemplateList.collect { [id: it[0], name: it[1], templateId: it[2], stillActive: it[3],
                                                              displayText: it[4], instanceCount: it[5]]}
        [templateInstanceList: templateInstanceList, templateInstanceTotal: templateInstanceTotal]
   
    }
    /*
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [templateInstanceList: Template.list(params), templateInstanceTotal: Template.count()]
        }*/

    def create() {
        [templateInstance: new Template(params)]
    }

    def save() {
        def templateInstance = new Template(params)
        if (!templateInstance.save(flush: true)) {
            render(view: "create", model: [templateInstance: templateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'template.label', default: 'Template'), templateInstance.id])
        redirect(action: "show", id: templateInstance.id)
    }

    def show(Long id) {
        def templateInstance = Template.get(id)
        if (!templateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'template.label', default: 'Template'), id])
            redirect(action: "list")
            return
        }

        [templateInstance: templateInstance]
    }

    def find(String id) {
        def templateInstance = Template.findByTemplateId(id)
        if (!templateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'template.label', default: 'Template'), id])
            redirect(action: "list")
            return
        }
        redirect(action: "show", id: templateInstance.id)
    }

    def edit(Long id) {
        def templateInstance = Template.get(id)
        if (!templateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'template.label', default: 'Template'), id])
            redirect(action: "list")
            return
        }

        [templateInstance: templateInstance]
    }

    def update(Long id, Long version) {
        def templateInstance = Template.get(id)
        if (!templateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'template.label', default: 'Template'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (templateInstance.version > version) {
                templateInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'template.label', default: 'Template')] as Object[],
                          "Another user has updated this Template while you were editing")
                render(view: "edit", model: [templateInstance: templateInstance])
                return
            }
        }

        templateInstance.properties = params

        if (!templateInstance.save(flush: true)) {
            render(view: "edit", model: [templateInstance: templateInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'template.label', default: 'Template'), templateInstance.id])
        redirect(action: "show", id: templateInstance.id)
    }

    def delete(Long id) {
        def templateInstance = Template.get(id)
        if (!templateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'template.label', default: 'Template'), id])
            redirect(action: "list")
            return
        }

        try {
            templateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'template.label', default: 'Template'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'template.label', default: 'Template'), id])
            redirect(action: "show", id: id)
        }
    }
}
