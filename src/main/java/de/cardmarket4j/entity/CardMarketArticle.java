package de.cardmarket4j.entity;

import java.math.BigDecimal;

import com.neovisionaries.i18n.LanguageCode;

import de.cardmarket4j.entity.enumeration.Condition;

public interface CardMarketArticle {
	int getArticleId();

	String getComment();

	Condition getCondition();

	LanguageCode getLanguageCode();

	BigDecimal getPrice();

	int getProductId();

	int getQuantity();

	boolean isAltered();

	boolean isFirstEdition();

	boolean isFoil();

	boolean isPlayset();

	boolean isSigned();
}
