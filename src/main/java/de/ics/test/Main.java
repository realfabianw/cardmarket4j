package de.ics.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.enums.Condition;
import de.ics.cardmarket4j.enums.Language;
import de.ics.cardmarket4j.enums.OrderState;
import de.ics.cardmarket4j.enums.OrderType;
import de.ics.cardmarket4j.structs.Article;
import de.ics.cardmarket4j.structs.ArticleFilter;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger("Main");

	public static void main(String[] args) throws IOException {
		CardMarketService market = new CardMarketService("Cr0yANU52r7iDhlQ", "EWIRcYdR7pnHWdLys8uJQr3aPgLZFXgx",
				"SnR9w5ZMT0kQwyfT2ahjqNZr7I0vKJcl", "bajBeBYsv5B4O1UA1MjGOG3AaZiAJpWM");

		// 368128
		ArticleFilter filter = new ArticleFilter();
		filter.setAltered(false);
		filter.setFoil(false);
		filter.setLanguage(Language.ENGLISH);
		filter.setMinCondition(Condition.NEAR_MINT);
		filter.setSigned(false);
		List<Article> listArticle = market.getMarketplaceService().getArticles(368128, filter);
		
		for (Article article : listArticle) {
			LOGGER.info(article.toString());
		}
		
	}
}
