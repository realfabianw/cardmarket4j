package de.ics.cardmarket4j.structs;

import java.math.BigDecimal;

import com.neovisionaries.i18n.LanguageCode;

public interface IsCardMarketArticle {
	public int getArticleId();

	public String getComment();

	public LanguageCode getLanguageCode();

	public BigDecimal getPrice();

	public int getProductId();

	public int getQuantity();
}
