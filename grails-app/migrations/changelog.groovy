databaseChangeLog = {

	changeSet(author: "ubuntu (generated)", id: "1353875593146-1") {
		createTable(tableName: "alert") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "alertPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "alert_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "sent", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(255)")
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-2") {
		createTable(tableName: "audit_log") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "audit_logPK")
			}

			column(name: "actor", type: "varchar(255)")

			column(name: "class_name", type: "varchar(255)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "event_name", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "new_value", type: "varchar(255)")

			column(name: "old_value", type: "varchar(255)")

			column(name: "persisted_object_id", type: "varchar(255)")

			column(name: "persisted_object_version", type: "bigint")

			column(name: "property_name", type: "varchar(255)")

			column(name: "uri", type: "varchar(255)")
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-3") {
		createTable(tableName: "cloud_stack_config") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cloud_stack_cPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "alert_email_from", type: "varchar(255)")

			column(name: "alert_email_to", type: "varchar(255)")

			column(name: "api_key", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "email_domain", type: "varchar(255)")

			column(name: "end_point_url", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "max_prov_destroy", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "secret_key", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-4") {
		createTable(tableName: "cpu_usage") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "cpu_usagePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cpu_usage", type: "float(19)")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "instance_id", type: "bigint")
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-5") {
		createTable(tableName: "host") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "hostPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "allocation_state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "average_load", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "capabilities", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "cluster_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "cluster_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "cluster_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "cpu_allocated", type: "varchar(255)")

			column(name: "cpu_number", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "cpu_speed", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "cpu_used", type: "varchar(255)")

			column(name: "cpu_with_over_provisioning", type: "float(19)") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "disconnected", type: "datetime")

			column(name: "disk_size_allocated", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "disk_size_total", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "events", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "has_enough_capacity", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "host_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "hypervisor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ip_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "job_id", type: "varchar(255)")

			column(name: "job_status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_pinged", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "local_storage_active", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "management_server_id", type: "varchar(255)")

			column(name: "memory_allocated", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "memory_total", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "memory_used", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_kbs_read", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "network_kbs_write", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "os_category_id", type: "varchar(255)")

			column(name: "os_category_name", type: "varchar(255)")

			column(name: "pod_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "pod_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "removed", type: "datetime")

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "tags", type: "varchar(255)")

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "zone_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-6") {
		createTable(tableName: "instance") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "instancePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "haenabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "ipaddress", type: "varchar(255)")

			column(name: "isodisplay_text", type: "varchar(255)")

			column(name: "isoid", type: "varchar(255)")

			column(name: "isoname", type: "varchar(255)")

			column(name: "account", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "cpu_count", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cpu_speed", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cpu_used", type: "varchar(255)")

			column(name: "created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "cs_template_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "display_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "domain", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "domain_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "group_id", type: "varchar(255)")

			column(name: "group_name", type: "varchar(255)")

			column(name: "guestosid", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "host_id", type: "bigint")

			column(name: "hostname", type: "varchar(255)")

			column(name: "hypervisor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "instance_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "instance_password", type: "varchar(255)")

			column(name: "job_id", type: "varchar(255)")

			column(name: "job_status", type: "integer")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "memory", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_kbs_read", type: "bigint")

			column(name: "network_kbs_write", type: "bigint")

			column(name: "password_enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "publicipid", type: "varchar(255)")

			column(name: "publicips", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "root_device_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "root_device_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "service_offering_id", type: "bigint")

			column(name: "service_offering_cs_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "service_offering_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "template_id", type: "bigint")

			column(name: "template_display_text", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "template_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "uses_virtual_network", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "zone_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-7") {
		createTable(tableName: "logging") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "loggingPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "category", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "msg", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "user", type: "varchar(255)")
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-8") {
		createTable(tableName: "notify_down_host") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "notify_down_hPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "current_state", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "down_since", type: "datetime")

			column(name: "host_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "host_name", type: "varchar(255)")

			column(name: "up_since", type: "datetime")
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-9") {
		createTable(tableName: "notify_seen_alerts") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "notify_seen_aPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "alert_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-10") {
		createTable(tableName: "provisioned_instance") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "provisioned_iPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime")

			column(name: "error_msg", type: "longtext")

			column(name: "hostname", type: "varchar(255)")

			column(name: "instance_id", type: "varchar(255)")

			column(name: "instance_name", type: "varchar(255)")

			column(name: "provision_status", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "provisioned_instance_group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "public_ip", type: "varchar(255)")

			column(name: "role", type: "varchar(255)")
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-11") {
		createTable(tableName: "provisioned_instance_group") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "provisioned_iPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "alternate_service_offering_id", type: "bigint")

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "expiration_time", type: "datetime")

			column(name: "init_script", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "offset", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "primary_count", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "primary_service_offering_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "provision_status", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "secondary_count", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "send_email", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "short_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "team_id", type: "bigint")

			column(name: "template_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "when_decommissioned", type: "datetime")
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-12") {
		createTable(tableName: "registration_code") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "registration_PK")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-13") {
		createTable(tableName: "report_run") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "report_runPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "completed", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-14") {
		createTable(tableName: "report_run_hosts") {
			column(name: "host_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "report_run_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-15") {
		createTable(tableName: "report_run_instances") {
			column(name: "instance_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "report_run_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-16") {
		createTable(tableName: "sec_role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sec_rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-17") {
		createTable(tableName: "sec_user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sec_userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)")

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-18") {
		createTable(tableName: "sec_user_sec_role") {
			column(name: "sec_role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "sec_user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-19") {
		createTable(tableName: "service_offering") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "service_offerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cpu_number", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "cpu_speed", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "cpu_use_limited", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "default_use", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "display_text", type: "varchar(255)")

			column(name: "domain", type: "varchar(255)")

			column(name: "domain_id", type: "varchar(255)")

			column(name: "ha_support", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "memory", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_rate", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "offering_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "still_active", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "storage_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "system_offering", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "system_vm_type", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "tags", type: "varchar(255)")
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-20") {
		createTable(tableName: "spring_security_event") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "spring_securiPK")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "event_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "remote_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "session_id", type: "varchar(255)")

			column(name: "username", type: "varchar(255)")
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-21") {
		createTable(tableName: "template") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "templatePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "ostype", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ostype_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "account", type: "varchar(255)")

			column(name: "account_id", type: "varchar(255)")

			column(name: "bootable", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "checksum", type: "varchar(255)")

			column(name: "created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "cross_zones", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "display_text", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "domain", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "domain_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "extractable", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "featured", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "format", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "host_id", type: "varchar(255)")

			column(name: "host_name", type: "varchar(255)")

			column(name: "hypervisor", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ispublic", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "job_id", type: "varchar(255)")

			column(name: "job_status", type: "varchar(255)")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "ready", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "removed", type: "datetime")

			column(name: "size", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "source_template_id", type: "varchar(255)")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "still_active", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "template_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "template_tag", type: "varchar(255)")

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "zone", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-22") {
		addPrimaryKey(columnNames: "report_run_id, host_id", tableName: "report_run_hosts")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-23") {
		addPrimaryKey(columnNames: "report_run_id, instance_id", tableName: "report_run_instances")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-24") {
		addPrimaryKey(columnNames: "sec_role_id, sec_user_id", constraintName: "sec_user_sec_PK", tableName: "sec_user_sec_role")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-25") {
		addForeignKeyConstraint(baseColumnNames: "instance_id", baseTableName: "cpu_usage", constraintName: "FK9EF6844AB96091D5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "instance", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-26") {
		addForeignKeyConstraint(baseColumnNames: "host_id", baseTableName: "instance", constraintName: "FK21169495383AEA75", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "host", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-27") {
		addForeignKeyConstraint(baseColumnNames: "service_offering_id", baseTableName: "instance", constraintName: "FK21169495E14882AA", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "service_offering", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-28") {
		addForeignKeyConstraint(baseColumnNames: "template_id", baseTableName: "instance", constraintName: "FK2116949530494BB5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "template", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-29") {
		addForeignKeyConstraint(baseColumnNames: "provisioned_instance_group_id", baseTableName: "provisioned_instance", constraintName: "FKE7227C401ABAE94B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "provisioned_instance_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-30") {
		addForeignKeyConstraint(baseColumnNames: "alternate_service_offering_id", baseTableName: "provisioned_instance_group", constraintName: "FKEBE1A5C0F92F4F65", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "service_offering", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-31") {
		addForeignKeyConstraint(baseColumnNames: "primary_service_offering_id", baseTableName: "provisioned_instance_group", constraintName: "FKEBE1A5C039B308ED", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "service_offering", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-32") {
		addForeignKeyConstraint(baseColumnNames: "team_id", baseTableName: "provisioned_instance_group", constraintName: "FKEBE1A5C09C277F09", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sec_role", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-33") {
		addForeignKeyConstraint(baseColumnNames: "template_id", baseTableName: "provisioned_instance_group", constraintName: "FKEBE1A5C030494BB5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "template", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-34") {
		addForeignKeyConstraint(baseColumnNames: "host_id", baseTableName: "report_run_hosts", constraintName: "FK91B5E7CC383AEA75", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "host", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-35") {
		addForeignKeyConstraint(baseColumnNames: "report_run_id", baseTableName: "report_run_hosts", constraintName: "FK91B5E7CC5A4CE096", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "report_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-36") {
		addForeignKeyConstraint(baseColumnNames: "instance_id", baseTableName: "report_run_instances", constraintName: "FK9D3E3DFFB96091D5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "instance", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-37") {
		addForeignKeyConstraint(baseColumnNames: "report_run_id", baseTableName: "report_run_instances", constraintName: "FK9D3E3DFF5A4CE096", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "report_run", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-38") {
		addForeignKeyConstraint(baseColumnNames: "sec_role_id", baseTableName: "sec_user_sec_role", constraintName: "FK6630E2A6ABF6062", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sec_role", referencesUniqueColumn: "false")
	}

	changeSet(author: "ubuntu (generated)", id: "1353875593146-39") {
		addForeignKeyConstraint(baseColumnNames: "sec_user_id", baseTableName: "sec_user_sec_role", constraintName: "FK6630E2AFEA2442", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sec_user", referencesUniqueColumn: "false")
	}


	include file: 'add-network-options-to-cscfg.groovy'
}
