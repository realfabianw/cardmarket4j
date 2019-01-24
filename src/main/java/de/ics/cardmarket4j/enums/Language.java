package de.ics.cardmarket4j.enums;

/**
 * 
 * @author QUE
 * @version 19.01.2018 - complete list at this date
 */
public enum Language {
	ENGLISH(1, "English"), FRENCH(2, "French"), GERMAN(3, "German"), SPANISH(4, "Spanish"), ITALIAN(5, "Italian"),
	SIMPLIFIED_CHINESE(6, "Simplified Chinese"), JAPANESE(7, "Japanese"), PORTUGUESE(8, "Portuguese"),
	RUSSIAN(9, "Russian"), KOREAN(10, "Korean"), TRADITIONAL_CHINESE(11, "Traditional Chinese");

	public static Language parseId(int id) {
		for (Language e : Language.values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	public static Language parseValue(String value) {
		for (Language e : Language.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;
	private final int id;

	Language(int id, String displayValue) {
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
