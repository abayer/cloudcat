databaseChangeLog = {

	changeSet(author: "abayer (generated)", id: "1353967548316-1") {
		addColumn(tableName: "cloud_stack_config") {
			column(name: "network_id", type: "varchar(255)")
		}
	}

	changeSet(author: "abayer (generated)", id: "1353967548316-2") {
		addColumn(tableName: "cloud_stack_config") {
			column(name: "setup_static_nat", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "abayer (generated)", id: "1353967548316-3") {
		createIndex(indexName: "FK91B5E7CC5A4CE096", tableName: "report_run_hosts") {
			column(name: "report_run_id")
		}
	}

	changeSet(author: "abayer (generated)", id: "1353967548316-4") {
		createIndex(indexName: "FK9D3E3DFF5A4CE096", tableName: "report_run_instances") {
			column(name: "report_run_id")
		}
	}

	changeSet(author: "abayer (generated)", id: "1353967548316-5") {
		createIndex(indexName: "authority_unique_1353967546927", tableName: "sec_role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "abayer (generated)", id: "1353967548316-6") {
		createIndex(indexName: "username_unique_1353967546932", tableName: "sec_user", unique: "true") {
			column(name: "username")
		}
	}

	changeSet(author: "abayer (generated)", id: "1353967548316-7") {
		createIndex(indexName: "FK6630E2A6ABF6062", tableName: "sec_user_sec_role") {
			column(name: "sec_role_id")
		}
	}
}
