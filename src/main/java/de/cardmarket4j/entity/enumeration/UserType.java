package de.cardmarket4j.entity.enumeration;

public enum UserType {
	PRIVATE(0, "private"), COMMERCIAL(1, "commercial"), POWERSELLER(2, "powerseller");

	public static UserType parseId(int id) {
		for (UserType e : UserType.values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	public static UserType parseValue(String value) {
		for (UserType e : UserType.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;
	private final int id;

	UserType(int id, String displayValue) {
		this.id = id;
		this.displayValue = displayValue;
	}

	private String getDisplayValue() {
		return displayValue;
	}

	private int getId() {
		return id;
	}

	@Override
	public String toString() {
		return displayValue;
	}
}
