import cloudstack.reporting.CloudStackConfig
import cloudstack.reporting.ImportInstancesJob
import cloudstack.reporting.ReportRun
import cloudstack.reporting.SecRole
import cloudstack.reporting.SecUser
import cloudstack.reporting.SecUserSecRole

class BootStrap {

    def init = { servletContext ->
        if (!CloudStackConfig.count()) {
            def csCfg = new CloudStackConfig()
            csCfg.endPointUrl = "YOUR_ENDPOINT"
            csCfg.apiKey = "YOUR_API_KEY"
            csCfg.secretKey = "YOUR_SECRET_KEY"
            csCfg.alertEmailFrom = "YOUR_FROM_EMAIL"
            csCfg.alertEmailTo = "YOUR_TO_EMAIL"
            csCfg.save()
        }
        def adminRole = new SecRole(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new SecRole(authority: 'ROLE_USER').save(flush: true)
        def demoTeam = new SecRole(authority: 'DemoTeam').save(flush: true)
        
        def testUser = new SecUser(username: 'YOUR_ADMIN_USER', password: 'YOUR_ADMIN_USER_PASSWORD', enabled: true)
        testUser.save(flush: true)

        SecUserSecRole.create testUser, adminRole, true
        
        ImportInstancesJob.triggerNow()
    }
    def destroy = {
    }
}

