package de.ics.cardmarket4j.structs;

import de.ics.cardmarket4j.enums.Condition;

public interface IsCardMarketCard extends IsCardMarketArticle {
	public Condition getCondition();

	public boolean isAltered();

	public boolean isFoil();

	public boolean isSigned();
}
