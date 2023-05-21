package de.cardmarket4j.entity.enumeration;

public enum DeliverySpeed {
	UNKNOWN(-1, "unknown shipping speed"), NORMAL(0, "normal shipping speed"), VERY_FAST(1, "very fast shipping"),
	FAST(2, "fast shipping");

	public static DeliverySpeed parseId(int id) {
		for (DeliverySpeed e : DeliverySpeed.values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	public static DeliverySpeed parseValue(String value) {
		for (DeliverySpeed e : DeliverySpeed.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;
	private final int id;

	DeliverySpeed(int id, String displayValue) {
		this.displayValue = displayValue;
		this.id = id;
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
