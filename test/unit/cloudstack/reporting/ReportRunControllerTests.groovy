package cloudstack.reporting



import org.junit.*
import grails.test.mixin.*

@TestFor(ReportRunController)
@Mock(ReportRun)
class ReportRunControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/reportRun/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reportRunInstanceList.size() == 0
        assert model.reportRunInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.reportRunInstance != null
    }

    void testSave() {
        controller.save()

        assert model.reportRunInstance != null
        assert view == '/reportRun/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reportRun/show/1'
        assert controller.flash.message != null
        assert ReportRun.count() == 1
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

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reportRun/list'

        populateValidParams(params)
        def reportRun = new ReportRun(params)

        assert reportRun.save() != null

        params.id = reportRun.id

        def model = controller.edit()

        assert model.reportRunInstance == reportRun
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reportRun/list'

        response.reset()

        populateValidParams(params)
        def reportRun = new ReportRun(params)

        assert reportRun.save() != null

        // test invalid parameters in update
        params.id = reportRun.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/reportRun/edit"
        assert model.reportRunInstance != null

        reportRun.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reportRun/show/$reportRun.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reportRun.clearErrors()

        populateValidParams(params)
        params.id = reportRun.id
        params.version = -1
        controller.update()

        assert view == "/reportRun/edit"
        assert model.reportRunInstance != null
        assert model.reportRunInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reportRun/list'

        response.reset()

        populateValidParams(params)
        def reportRun = new ReportRun(params)

        assert reportRun.save() != null
        assert ReportRun.count() == 1

        params.id = reportRun.id

        controller.delete()

        assert ReportRun.count() == 0
        assert ReportRun.get(reportRun.id) == null
        assert response.redirectedUrl == '/reportRun/list'
    }
}
