package cloudstack.reporting



import org.junit.*
import grails.test.mixin.*

@TestFor(LoggingController)
@Mock(Logging)
class LoggingControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/logging/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.loggingInstanceList.size() == 0
        assert model.loggingInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.loggingInstance != null
    }

    void testSave() {
        controller.save()

        assert model.loggingInstance != null
        assert view == '/logging/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/logging/show/1'
        assert controller.flash.message != null
        assert Logging.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/logging/list'

        populateValidParams(params)
        def logging = new Logging(params)

        assert logging.save() != null

        params.id = logging.id

        def model = controller.show()

        assert model.loggingInstance == logging
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/logging/list'

        populateValidParams(params)
        def logging = new Logging(params)

        assert logging.save() != null

        params.id = logging.id

        def model = controller.edit()

        assert model.loggingInstance == logging
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/logging/list'

        response.reset()

        populateValidParams(params)
        def logging = new Logging(params)

        assert logging.save() != null

        // test invalid parameters in update
        params.id = logging.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/logging/edit"
        assert model.loggingInstance != null

        logging.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/logging/show/$logging.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        logging.clearErrors()

        populateValidParams(params)
        params.id = logging.id
        params.version = -1
        controller.update()

        assert view == "/logging/edit"
        assert model.loggingInstance != null
        assert model.loggingInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/logging/list'

        response.reset()

        populateValidParams(params)
        def logging = new Logging(params)

        assert logging.save() != null
        assert Logging.count() == 1

        params.id = logging.id

        controller.delete()

        assert Logging.count() == 0
        assert Logging.get(logging.id) == null
        assert response.redirectedUrl == '/logging/list'
    }
}
