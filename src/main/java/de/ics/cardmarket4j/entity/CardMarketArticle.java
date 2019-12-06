package de.ics.cardmarket4j.entity;

import java.math.BigDecimal;

import com.neovisionaries.i18n.LanguageCode;

import de.ics.cardmarket4j.entity.enumeration.Condition;

public interface CardMarketArticle {
	public int getArticleId();

	public String getComment();

	public Condition getCondition();

	public LanguageCode getLanguageCode();

	public BigDecimal getPrice();

	public int getProductId();

	public int getQuantity();

	public boolean isAltered();

	public boolean isFirstEdition();

	public boolean isFoil();

	public boolean isPlayset();

	public boolean isSigned();
}
