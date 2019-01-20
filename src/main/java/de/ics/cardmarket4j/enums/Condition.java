package de.ics.cardmarket4j.enums;

/**
 * 
 * @author QUE
 * @version 19.01.2018 - complete list at this date
 */
public enum Condition {
	MINT("MT", "Mint"), NEAR_MINT("NM", "Near Mint"), EXCELLENT("EX", "Excellent"), GOOD("GD", "Good"),
	LIGHT_PLAYED("LP", "Light Played"), PLAYED("PL", "Played"), POOR("PO", "Poor");

	public static Condition parseId(String id) {
		for (Condition e : Condition.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
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
