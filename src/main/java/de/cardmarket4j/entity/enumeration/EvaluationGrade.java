package de.cardmarket4j.entity.enumeration;

public enum EvaluationGrade {
	VERYGOOD(1, "very good"), GOOD(2, "good"), NEUTRAL(3, "neutral"), BAD(4, "bad"), NA(10, "n/a");

	public static EvaluationGrade parseId(int id) {
		for (EvaluationGrade e : EvaluationGrade.values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	public static EvaluationGrade parseValue(String value) {
		for (EvaluationGrade e : EvaluationGrade.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;
	private final int id;

	EvaluationGrade(int id, String displayValue) {
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
