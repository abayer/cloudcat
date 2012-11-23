package cloudstack.reporting



import org.junit.*
import grails.test.mixin.*

@TestFor(ProvisionedInstanceGroupController)
@Mock(ProvisionedInstanceGroup)
class ProvisionedInstanceGroupControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/provisionedInstanceGroup/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.provisionedInstanceGroupInstanceList.size() == 0
        assert model.provisionedInstanceGroupInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.provisionedInstanceGroupInstance != null
    }

    void testSave() {
        controller.save()

        assert model.provisionedInstanceGroupInstance != null
        assert view == '/provisionedInstanceGroup/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/provisionedInstanceGroup/show/1'
        assert controller.flash.message != null
        assert ProvisionedInstanceGroup.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/provisionedInstanceGroup/list'

        populateValidParams(params)
        def provisionedInstanceGroup = new ProvisionedInstanceGroup(params)

        assert provisionedInstanceGroup.save() != null

        params.id = provisionedInstanceGroup.id

        def model = controller.show()

        assert model.provisionedInstanceGroupInstance == provisionedInstanceGroup
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/provisionedInstanceGroup/list'

        populateValidParams(params)
        def provisionedInstanceGroup = new ProvisionedInstanceGroup(params)

        assert provisionedInstanceGroup.save() != null

        params.id = provisionedInstanceGroup.id

        def model = controller.edit()

        assert model.provisionedInstanceGroupInstance == provisionedInstanceGroup
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/provisionedInstanceGroup/list'

        response.reset()

        populateValidParams(params)
        def provisionedInstanceGroup = new ProvisionedInstanceGroup(params)

        assert provisionedInstanceGroup.save() != null

        // test invalid parameters in update
        params.id = provisionedInstanceGroup.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/provisionedInstanceGroup/edit"
        assert model.provisionedInstanceGroupInstance != null

        provisionedInstanceGroup.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/provisionedInstanceGroup/show/$provisionedInstanceGroup.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        provisionedInstanceGroup.clearErrors()

        populateValidParams(params)
        params.id = provisionedInstanceGroup.id
        params.version = -1
        controller.update()

        assert view == "/provisionedInstanceGroup/edit"
        assert model.provisionedInstanceGroupInstance != null
        assert model.provisionedInstanceGroupInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/provisionedInstanceGroup/list'

        response.reset()

        populateValidParams(params)
        def provisionedInstanceGroup = new ProvisionedInstanceGroup(params)

        assert provisionedInstanceGroup.save() != null
        assert ProvisionedInstanceGroup.count() == 1

        params.id = provisionedInstanceGroup.id

        controller.delete()

        assert ProvisionedInstanceGroup.count() == 0
        assert ProvisionedInstanceGroup.get(provisionedInstanceGroup.id) == null
        assert response.redirectedUrl == '/provisionedInstanceGroup/list'
    }
}
