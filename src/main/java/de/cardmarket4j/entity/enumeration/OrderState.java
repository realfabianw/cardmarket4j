package de.cardmarket4j.entity.enumeration;

public enum OrderState {
	BOUGHT(1, "bought"), PAID(2, "paid"), SENT(4, "sent"), RECEIVED(8, "received"), LOST(32, "lost"),
	CANCELLED(128, "cancelled");

	public static OrderState parseId(int id) {
		for (OrderState e : OrderState.values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	public static OrderState parseValue(String value) {
		for (OrderState e : OrderState.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		if (value.equals("evaluated")) {
			return OrderState.RECEIVED;
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;
	private final int id;

	OrderState(int id, String displayValue) {
		this.displayValue = displayValue;
		this.id = id;
	}

	public String getDisplayValue() {
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
