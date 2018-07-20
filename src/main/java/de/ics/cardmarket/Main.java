package de.ics.cardmarket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger("CardMarket");
	private static final String appToken = "Cr0yANU52r7iDhlQ";
	private static final String appSecret = "EWIRcYdR7pnHWdLys8uJQr3aPgLZFXgx";
	
	private static final String accessToken = "SnR9w5ZMT0kQwyfT2ahjqNZr7I0vKJcl";
	private static final String accessTokenSecret = "bajBeBYsv5B4O1UA1MjGOG3AaZiAJpWM";
	
	public static void main(String[] args) {
		String requestNew ="account";
		
		CardMarket appNew = new CardMarket(appToken, appSecret, accessToken, accessTokenSecret);	
		LOGGER.info(appNew.request(requestNew).toString());

	}
}
