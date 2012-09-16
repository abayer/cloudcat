package cloudstack.reporting

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.logging.LogFactory

class ReportRunController {
    private static final log = LogFactory.getLog(this)

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = params.sort ?: "id"
        params.order = params.order ?: "desc"
        [reportRunList: ReportRun.list(params), reportRunTotal: ReportRun.count()]
    }

    def report() {
        params.sort = params.sort ?: "total"
        params.order = params.order ?: "desc"
        def latestReportRunId = ReportRun.where { }.projections { max 'id' }.find()
        def reportRunId = params.reportRunId ?: latestReportRunId

        def latestReportRun = ReportRun.get(reportRunId)
        
        def instanceSizes = Instance.executeQuery('select distinct serviceOfferingName from Instance')
        //        def accounts = Instance.where { }.projections { distinct: 'account' }
        def accounts = Instance.executeQuery('select distinct account from Instance')
        
        def rawInstCounts = Instance.executeQuery("select i.account, i.serviceOfferingName, count(i) from ReportRun as r join r.instances as i where r.id = ${reportRunId} group by i.account, i.serviceOfferingName")
        def accountInstancesMap = [:]

        accounts.each { a ->
            accountInstancesMap[a] = [:]
            
            accountInstancesMap[a]['account'] = a
            accountInstancesMap[a]['total'] = 0
        }
        rawInstCounts.each { r ->
            accountInstancesMap[r[0]][r[1]] = r[2]
            accountInstancesMap[r[0]]['total'] += r[2]
        }


        def accountInstances = accountInstancesMap.values().sort { it[params.sort] }
        if (params.order == 'desc') {
            accountInstances = accountInstances.reverse()
        }
        [instanceSizes:instanceSizes, accountInstances: accountInstances, latestReportRun:latestReportRun]

    }

    def create() {
        [reportRunInstance: new ReportRun(params)]
    }

    def save() {
        def reportRunInstance = new ReportRun(params)
        if (!reportRunInstance.save(flush: true)) {
            render(view: "create", model: [reportRunInstance: reportRunInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reportRun.label', default: 'ReportRun'), reportRunInstance.id])
        redirect(action: "show", id: reportRunInstance.id)
    }

    def show(Long id) {
        def reportRunInstance = ReportRun.get(id)
        if (!reportRunInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportRun.label', default: 'ReportRun'), id])
            redirect(action: "list")
            return
        }

        [reportRunInstance: reportRunInstance]
    }

    def edit(Long id) {
        def reportRunInstance = ReportRun.get(id)
        if (!reportRunInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportRun.label', default: 'ReportRun'), id])
            redirect(action: "list")
            return
        }

        [reportRunInstance: reportRunInstance]
    }

    def update(Long id, Long version) {
        def reportRunInstance = ReportRun.get(id)
        if (!reportRunInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportRun.label', default: 'ReportRun'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reportRunInstance.version > version) {
                reportRunInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reportRun.label', default: 'ReportRun')] as Object[],
                          "Another user has updated this ReportRun while you were editing")
                render(view: "edit", model: [reportRunInstance: reportRunInstance])
                return
            }
        }

        reportRunInstance.properties = params

        if (!reportRunInstance.save(flush: true)) {
            render(view: "edit", model: [reportRunInstance: reportRunInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reportRun.label', default: 'ReportRun'), reportRunInstance.id])
        redirect(action: "show", id: reportRunInstance.id)
    }

    def delete(Long id) {
        def reportRunInstance = ReportRun.get(id)
        if (!reportRunInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reportRun.label', default: 'ReportRun'), id])
            redirect(action: "list")
            return
        }

        try {
            reportRunInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reportRun.label', default: 'ReportRun'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reportRun.label', default: 'ReportRun'), id])
            redirect(action: "show", id: id)
        }
    }
}
