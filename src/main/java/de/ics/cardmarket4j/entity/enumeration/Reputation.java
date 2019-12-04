package de.ics.cardmarket4j.entity.enumeration;

public enum Reputation {
	NO_REPUTATION(0, "not enough sales to rate"), OUTSTANDING_SELLER(1, "outstanding seller"),
	VERY_GOOD_SELLER(2, "very good seller"), GOOD_SELLER(3, "good seller"), AVERAGE_SELLER(4, "average seller"),
	BAD_SELLER(5, "bad seller");

	public static Reputation parseId(int id) {
		for (Reputation e : Reputation.values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	public static Reputation parseValue(String value) {
		for (Reputation e : Reputation.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;

	private final int id;

	private Reputation(int id, String displayValue) {
		this.displayValue = displayValue;
		this.id = id;
	}

	private String getDisplayValue() {
		return displayValue;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return displayValue;
	}
}
