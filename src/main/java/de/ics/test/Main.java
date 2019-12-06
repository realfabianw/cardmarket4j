package de.ics.test;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.entity.Article;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger("Main");

	public static void main(String[] args) throws IOException, InterruptedException {
		CardMarketService market = new CardMarketService("wnyJdqZJHmy2eQ28", "Jh7aj2jiR5REFMbeKn2VijlEpjwK1nf3",
				"YHEpS1HRHkIjixZFJAHfdqHVw3r3ZS7C", "VtADsKQR7OUTN77QP8IX2DkKetxo5kU9");

		// LEA Lightning Bolt 5689
		// Lightning Bolt Playmat 397929
//		ArticleFilter aF = new ArticleFilter();
//		aF.setMaxResults(1);
//		List<Article> listArticles = market.getMarketplaceService().getArticles(5689, aF);
//
//		for (Article a : listArticles) {
//			LOGGER.info(a.toString());
//		}

//		List<Order> listOrders = market.getOrderService().getOrders(OrderType.SALE, OrderState.RECEIVED, 1);
//
//		for (Order o : listOrders) {
//			LOGGER.info(o.toString());
//		}

//		LOGGER.info("Specific Products");
//		LOGGER.info(market.getMarketplaceService().getProductDetails(5689).toString());
//		LOGGER.info(market.getMarketplaceService().getProductDetails(397929).toString());

		// Test: Stock Service
		List<Article> listArticles = market.getStockService().getStock();
		for (Article article : listArticles) {
			LOGGER.info(article.toString());
		}
	}
}
