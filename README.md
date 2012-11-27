# CloudCat

## Running CloudCat locally

First, make sure you've got mysql running, with the user/password root/cloudcat (or make changes to cloudcat.git/grails-app/conf/DataSource.groovy in your local repo, but don't commit/push 'em). Then clone [github.com/abayer/cloudcat](http://github.com/abayer/cloudcat). 

You'll need to modify cloudcat.git/grails-app/conf/BootStrap.groovy with your endpoint, creds, email, etc.

To run grails, go to the cloudcat directory and run "./grailsw". That'll do its own version of downloading the world. To run it, from within the grails session, do "run-app". You can also do "./grailsw run-app" from your normal shell. Now it'll start up at [localhost:8080/cloudcat](http://localhost:8080/cloudcat). Tada! It still won't be able to do anything with CloudStack - I've only used the CloudStack admin API key/secret key so far, so I can't guarantee how configuring it with your own key will work, but that's what I'd suggest.

So now you should be able to login, at the very least, and probably provision instances (though the reporting stuff may not work right - let me know if you end up with blank dropdowns for template and service offering, because that probably means permissions issues for your API key). The DB that gets used in mysql is cloudcat_dev (when you run via "run-app"). We're using the DB Migrations plugin for database changes, so I'd recommend doing the following first time you clone the repo, and again whenever you pull changes:

./grailsw prod dbm-update

That'll update the cloudcat database in mysql to the latest migration. If you're making changes to domain classes, you'll need to do "./grailsw prod dbm-gorm-diff (filename).groovy -add", and then add grails-app/migrations/changelog.groovy and .../(filename).groovy to your commit, and run "./grailsw prod dbm-update" locally as well. Probably a good idea to run "./grailsw prod dbm-validate" after the diff, actually.
