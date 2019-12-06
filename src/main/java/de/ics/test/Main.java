package de.ics.test;

import java.io.IOException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.entity.Product;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger("Main");

	public static void main(String[] args) throws IOException, InterruptedException {
		CardMarketService market = new CardMarketService("wnyJdqZJHmy2eQ28", "Jh7aj2jiR5REFMbeKn2VijlEpjwK1nf3",
				"YHEpS1HRHkIjixZFJAHfdqHVw3r3ZS7C", "VtADsKQR7OUTN77QP8IX2DkKetxo5kU9");

		LOGGER.info("List of Products");

		Set<Product> setProducts = market.getMarketplaceService().getProduct("Lightning Bolt");
		for (Product p : setProducts) {
			LOGGER.info(p.toString());
		}

		LOGGER.info("Specific Products");
		LOGGER.info(market.getMarketplaceService().getProductDetails(5689).toString());
		LOGGER.info(market.getMarketplaceService().getProductDetails(397929).toString());
	}
}
