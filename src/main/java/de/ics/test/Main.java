package de.ics.test;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ics.cardmarket4j.CardMarketService;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger("Main");

	public static void main(String[] args) throws IOException {
		CardMarketService market = new CardMarketService("Cr0yANU52r7iDhlQ", "EWIRcYdR7pnHWdLys8uJQr3aPgLZFXgx",
				"SnR9w5ZMT0kQwyfT2ahjqNZr7I0vKJcl", "bajBeBYsv5B4O1UA1MjGOG3AaZiAJpWM");
		market.getStockService().getStock();
//		market.getMarketplaceService().getProduct("Bone Saw");
//		market.getMarketplaceService().getProductDetailed(20778);

		// market.getStockService().insertArticle(new Article(20778, Language.ENGLISH,
		// 1, BigDecimal.valueOf(5), Condition.POOR, "API Test", false, false, false,
		// false));

		// LOGGER.info(market.getStockService().getArticleByArticleId(387789189).toString());

		// market.getStockService().getListArticlesByNameAndGame("Wild", Game.MTG);

//		List<Article> listStock = market.getStockService().getStock();
//		for (Article article : listStock) {
//			if (article.getProduct().getProductId() == 20778) {
//				market.getStockService().decreaseArticleQuantity(article);
////				LOGGER.info(article.toString());
////				article.setPrice(BigDecimal.valueOf(9));
////				LOGGER.info(article.toString());
////				LOGGER.info(market.getStockService().editArticle(article).toString());
////				LOGGER.info(market.getStockService().removeArticle(article).toString());
//			}
//		}
	}
}
