package de.ics.cardmarket4j.enums;

/**
 * 
 * @author QUE
 * @version 19.01.2018 - complete list at this date
 */
public enum Game {
	MTG(1, "Magic the Gathering"), YGO(3, "Yugioh"), PCG(6, "Pokémon"), DBS(13, "Dragon Ball Super"),
	FOW(7, "Force of Will"), FF(9, "Final Fantasy"), CFV(8, "Cardfight!! Vanguard"), WOW(2, "World of Warcraft TCG"),
	SWD(15, "Star Wars: Destiny"), WS(10, "Weiß Schwarz"), DGB(11, "Dragonborne"), MLP(12, "My Little Pony"),
	SPOILS(5, "The Spoils"), ACCESSORIES(99, "accessories");

	public static Game parseId(int id) {
		for (Game e : Game.values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + id);
	}

	public static Game parseValue(String value) {
		for (Game e : Game.values()) {
			if (e.getDisplayValue().equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
	}

	private final String displayValue;
	private final int id;

	Game(int id, String displayValue) {
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
