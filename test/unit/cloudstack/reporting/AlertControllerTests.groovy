package cloudstack.reporting



import org.junit.*
import grails.test.mixin.*

@TestFor(AlertController)
@Mock(Alert)
class AlertControllerTests {

    def populateValidParams(params) {
        params.alertId = "some-id"
        params.description = "some description"
        params.sent = new Date()
        params.type = "some type"
        
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/alert/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.alertInstanceList.size() == 0
        assert model.alertInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.alertInstance != null
    }

    void testSave() {
        controller.save()

        assert model.alertInstance != null
        assert view == '/alert/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/alert/show/1'
        assert controller.flash.message != null
        assert Alert.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/alert/list'

        populateValidParams(params)
        def alert = new Alert(params)

        assert alert.save() != null

        params.id = alert.id

        def model = controller.show()

        assert model.alertInstance == alert
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/alert/list'

        populateValidParams(params)
        def alert = new Alert(params)

        assert alert.save() != null

        params.id = alert.id

        def model = controller.edit()

        assert model.alertInstance == alert
    }
}
