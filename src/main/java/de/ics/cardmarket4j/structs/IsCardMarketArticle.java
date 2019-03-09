package de.ics.cardmarket4j.structs;

import java.math.BigDecimal;

import com.neovisionaries.i18n.LanguageCode;

import de.ics.cardmarket4j.enums.Condition;

public interface IsCardMarketArticle {
	public int getArticleId();

	public String getComment();

	public Condition getCondition();

	public LanguageCode getLanguageCode();

	public BigDecimal getPrice();

	public int getProductId();

	public int getQuantity();

	public boolean isAltered();

	public boolean isFoil();

	public boolean isSigned();

}
