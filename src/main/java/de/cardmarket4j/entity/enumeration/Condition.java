package de.cardmarket4j.entity.enumeration;

public enum Condition {
	MINT("MT", "Mint"), NEAR_MINT("NM", "Near Mint"), EXCELLENT("EX", "Excellent"), GOOD("GD", "Good"),
	LIGHT_PLAYED("LP", "Light Played"), PLAYED("PL", "Played"), POOR("PO", "Poor");

	public static Condition parseId(String id) {
		if (id != null) {
			for (Condition e : Condition.values()) {
				if (e.getId().equals(id)) {
					return e;
				}
			}
			throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
		} else {
			return null;
		}
	}

	public static Condition parseValue(String value) {
		for (Condition e : Condition.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;
	private final String id;

	Condition(String id, String displayValue) {
		this.displayValue = displayValue;
		this.id = id;
	}

	private String getDisplayValue() {
		return displayValue;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return displayValue;
	}
}
