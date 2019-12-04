package de.ics.cardmarket4j.entity.enumeration;

public enum SellerActivationStatus {
	NONE(0, "no seller activation"), REQUESTED(1, "seller activation requested, but transfers still pending"),
	PROCESSING(2, "transfers for requests processed"), ACTIVATED(3, "activated seller");

	public static SellerActivationStatus parseId(int id) {
		for (SellerActivationStatus e : SellerActivationStatus.values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	public static SellerActivationStatus parseValue(String value) {
		for (SellerActivationStatus e : SellerActivationStatus.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;
	private final int id;

	private SellerActivationStatus(int id, String displayValue) {
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
