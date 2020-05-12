package management.carspot;

public enum OwnerType {
	VISTOR("visitor"), STAFF_MEMBER("staff member");
	
	private final String value;
	
	public String getValue() {
		return value;
	}
	
	private OwnerType(String value) {
		this.value=value;
	}
}
