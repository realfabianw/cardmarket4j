package de.cardmarket4j.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import de.cardmarket4j.AbstractService;
import de.cardmarket4j.CardMarketService;
import de.cardmarket4j.entity.Order;
import de.cardmarket4j.entity.enumeration.HTTPMethod;
import de.cardmarket4j.entity.enumeration.OrderState;
import de.cardmarket4j.entity.enumeration.OrderType;
import de.cardmarket4j.util.JsonIO;

/**
 * OrderServices provides a connection to several order related functions on
 * cardmarket. TODO Add (Orders, GET/PUT)
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Order_Management
 * @author QUE
 *
 */
public class OrderService extends AbstractService {
	public OrderService(CardMarketService cardMarket) {
		super(cardMarket);
	}

	/**
	 * Returns all orders that match the given parameters.
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Filter_Orders
	 * @param orderType
	 * @param orderState
	 * @return {@code List<Order> listOrders}
	 * @throws IOException
	 * @throws InterruptedException
	 * @version 0.7
	 */
	public List<Order> getOrders(OrderType orderType, OrderState orderState, Integer maxPages)
			throws IOException, InterruptedException {
		List<Order> listOrders = new ArrayList<>();
		int page = 1;
		int index = 1;
		int responseCode = 200;
		while (responseCode == 200 || responseCode == 206) {
			JsonElement response = request(
					"orders/" + orderType.getDisplayValue() + "/" + orderState.getDisplayValue() + "/" + index,
					HTTPMethod.GET);
			responseCode = getLastResponse().getValue0();
			page++;
			index += 100;
			if (responseCode == 200 || responseCode == 206) {
				JsonArray responseJsonArray = response.getAsJsonObject().get("order").getAsJsonArray();
				if (responseJsonArray.size() > 0) {
					for (JsonElement jEle : responseJsonArray) {
						listOrders.add(JsonIO.getGson().fromJson(jEle, Order.class));
					}
					Thread.sleep(100);
					if (maxPages != null && page >= maxPages) {
						break;
					}
				} else {
					break;
				}
			}
		}
		return listOrders;
	}
}
