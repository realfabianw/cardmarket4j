package de.ics.cardmarket4j.entity.enumeration;

public enum Complaint {
	BADCOMMUNICATION("badCommunication", "bad communication"),
	INCOMPLETESHIPMENT("incompleteShipment", "incomplete shipment"), NOTFOIL("notFoil", "not foil"),
	RUDESELLER("rudeSeller", "rude seller"), SHIPDAMAGE("shipDamage", "shipment damage"),
	UNORDEREDSHIPMENT("unorderedShipment", "unordered shipment"), WRONGED("wrongEd", "wrong edition/set"),
	WRONGLANG("wrongLang", "wrong language");

	public static Complaint parseId(String id) {
		for (Complaint e : Complaint.values()) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	public static Complaint parseValue(String value) {
		for (Complaint e : Complaint.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;
	private final String id;

	Complaint(String id, String displayValue) {
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
