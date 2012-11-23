package cloudstack.reporting

class SecRole {

    static auditable = true
    
	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
