databaseChangeLog = {

	changeSet(author: "abayer (generated)", id: "1354222233808-1") {
		dropColumn(columnName: "version", tableName: "provisioned_instance")
	}
}
