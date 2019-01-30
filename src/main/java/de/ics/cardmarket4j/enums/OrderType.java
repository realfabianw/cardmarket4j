package de.ics.cardmarket4j.enums;

/**
 * 
 * @author QUE
 * @version 30.01.2019
 */
public enum OrderType {
	PURCHASE(2, "purchase"), SALE(1, "sale");

	public static OrderType parseId(int id) {
		for (OrderType e : OrderType.values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	public static OrderType parseValue(String value) {
		for (OrderType e : OrderType.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;
	private final int id;

	OrderType(int id, String displayValue) {
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
