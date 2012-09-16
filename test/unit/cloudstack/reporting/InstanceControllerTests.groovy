package cloudstack.reporting



import org.junit.*
import grails.test.mixin.*

@TestFor(InstanceController)
@Mock(Instance)
class InstanceControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/instance/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.instanceInstanceList.size() == 0
        assert model.instanceInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.instanceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.instanceInstance != null
        assert view == '/instance/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/instance/show/1'
        assert controller.flash.message != null
        assert Instance.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/instance/list'

        populateValidParams(params)
        def instance = new Instance(params)

        assert instance.save() != null

        params.id = instance.id

        def model = controller.show()

        assert model.instanceInstance == instance
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/instance/list'

        populateValidParams(params)
        def instance = new Instance(params)

        assert instance.save() != null

        params.id = instance.id

        def model = controller.edit()

        assert model.instanceInstance == instance
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/instance/list'

        response.reset()

        populateValidParams(params)
        def instance = new Instance(params)

        assert instance.save() != null

        // test invalid parameters in update
        params.id = instance.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/instance/edit"
        assert model.instanceInstance != null

        instance.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/instance/show/$instance.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        instance.clearErrors()

        populateValidParams(params)
        params.id = instance.id
        params.version = -1
        controller.update()

        assert view == "/instance/edit"
        assert model.instanceInstance != null
        assert model.instanceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/instance/list'

        response.reset()

        populateValidParams(params)
        def instance = new Instance(params)

        assert instance.save() != null
        assert Instance.count() == 1

        params.id = instance.id

        controller.delete()

        assert Instance.count() == 0
        assert Instance.get(instance.id) == null
        assert response.redirectedUrl == '/instance/list'
    }
}
