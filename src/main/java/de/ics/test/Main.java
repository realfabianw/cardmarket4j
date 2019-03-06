package de.ics.test;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ics.cardmarket4j.CardMarketService;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger("Main");

	public static void main(String[] args) throws IOException, InterruptedException {
		CardMarketService market = new CardMarketService("Cr0yANU52r7iDhlQ", "EWIRcYdR7pnHWdLys8uJQr3aPgLZFXgx",
				"SnR9w5ZMT0kQwyfT2ahjqNZr7I0vKJcl", "bajBeBYsv5B4O1UA1MjGOG3AaZiAJpWM");
		market.getMarketplaceService().getProduct("Skewer the Critics");
		// market.getOrderService().getOrders(OrderType.SALE, OrderState.RECEIVED);
		// List<Order> listOrders = market.getOrderService().getOrders(OrderType.SALE,
		// OrderState.RECEIVED);
	}
}
