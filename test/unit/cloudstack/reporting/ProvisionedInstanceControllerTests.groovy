package cloudstack.reporting



import org.junit.*
import grails.test.mixin.*

@TestFor(ProvisionedInstanceController)
@Mock(ProvisionedInstance)
class ProvisionedInstanceControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/provisionedInstance/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.provisionedInstanceInstanceList.size() == 0
        assert model.provisionedInstanceInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.provisionedInstanceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.provisionedInstanceInstance != null
        assert view == '/provisionedInstance/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/provisionedInstance/show/1'
        assert controller.flash.message != null
        assert ProvisionedInstance.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/provisionedInstance/list'

        populateValidParams(params)
        def provisionedInstance = new ProvisionedInstance(params)

        assert provisionedInstance.save() != null

        params.id = provisionedInstance.id

        def model = controller.show()

        assert model.provisionedInstanceInstance == provisionedInstance
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/provisionedInstance/list'

        populateValidParams(params)
        def provisionedInstance = new ProvisionedInstance(params)

        assert provisionedInstance.save() != null

        params.id = provisionedInstance.id

        def model = controller.edit()

        assert model.provisionedInstanceInstance == provisionedInstance
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/provisionedInstance/list'

        response.reset()

        populateValidParams(params)
        def provisionedInstance = new ProvisionedInstance(params)

        assert provisionedInstance.save() != null

        // test invalid parameters in update
        params.id = provisionedInstance.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/provisionedInstance/edit"
        assert model.provisionedInstanceInstance != null

        provisionedInstance.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/provisionedInstance/show/$provisionedInstance.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        provisionedInstance.clearErrors()

        populateValidParams(params)
        params.id = provisionedInstance.id
        params.version = -1
        controller.update()

        assert view == "/provisionedInstance/edit"
        assert model.provisionedInstanceInstance != null
        assert model.provisionedInstanceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/provisionedInstance/list'

        response.reset()

        populateValidParams(params)
        def provisionedInstance = new ProvisionedInstance(params)

        assert provisionedInstance.save() != null
        assert ProvisionedInstance.count() == 1

        params.id = provisionedInstance.id

        controller.delete()

        assert ProvisionedInstance.count() == 0
        assert ProvisionedInstance.get(provisionedInstance.id) == null
        assert response.redirectedUrl == '/provisionedInstance/list'
    }
}
