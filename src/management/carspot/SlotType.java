package management.carspot;

public enum SlotType {
	VISTOR("visitor"), STAFF_MEMBER("staff member");

	private final String value;

	public String getValue() {
		return value;
	}

	private SlotType(String value) {
		this.value = value;
	}
}
