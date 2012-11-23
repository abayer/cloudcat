package cloudstack.reporting


class ProvisionedInstanceStatus {
    final static instanceStatuses = [ "Unprovisioned",
                                      "Provisioning",
                                      "Provision Failed",
                                      "Provision Succeeded",
                                      "Ready to Destroy",
                                      "Destroying",
                                      "Destroyed",
                                      "Cancelled"
                                    ];
    
    final static groupStatuses = [ "Unprovisioned",
                                   "Provisioning",
                                   "Error Provisioning",
                                   "Provisioning Completed",
                                   "Destroying",
                                   "Destroyed",
                                   "Cancelled"
                                 ];
    
    final static instanceRoles = [ "Standard",
                                   "Alternate" ]
                                   
                        
}