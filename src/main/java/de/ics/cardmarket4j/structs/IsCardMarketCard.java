package de.ics.cardmarket4j.structs;

import de.ics.cardmarket4j.entity.enumeration.Condition;

public interface IsCardMarketCard extends IsCardMarketArticle {
	public Condition getCondition();

	public boolean isAltered();

	public boolean isFoil();

	public boolean isSigned();
}
