databaseChangeLog = {

	changeSet(author: "abayer (generated)", id: "1354049472940-1") {
		addColumn(tableName: "cloud_stack_config") {
			column(name: "instance_admin_password", type: "varchar(255)")
		}
	}

	changeSet(author: "abayer (generated)", id: "1354049472940-2") {
		addColumn(tableName: "cloud_stack_config") {
			column(name: "instance_admin_user", type: "varchar(255)")
		}
	}
}
