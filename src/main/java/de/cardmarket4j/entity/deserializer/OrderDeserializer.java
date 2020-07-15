package de.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.cardmarket4j.entity.Address;
import de.cardmarket4j.entity.Article;
import de.cardmarket4j.entity.Evaluation;
import de.cardmarket4j.entity.Order;
import de.cardmarket4j.entity.ShippingMethod;
import de.cardmarket4j.entity.User;
import de.cardmarket4j.entity.enumeration.OrderState;
import de.cardmarket4j.util.JsonIO;

public class OrderDeserializer implements JsonDeserializer<Order> {

	@Override
	public Order deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();

		int orderId = JsonIO.parseInteger(jObject, "idOrder");
		boolean isBuyer = JsonIO.parseBoolean(jObject, "isBuyer");
		User buyer = JsonIO.getGson().fromJson(jObject.get("buyer"), User.class);
		User seller = JsonIO.getGson().fromJson(jObject.get("seller"), User.class);

		JsonObject jState = jObject.get("state").getAsJsonObject();
		OrderState orderState = OrderState.parseValue(JsonIO.parseString(jState, "state"));
		LocalDateTime dateBought = JsonIO.parseLocalDateTime(jState, "dateBought", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime datePaid = JsonIO.parseLocalDateTime(jState, "datePaid", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime dateSent = JsonIO.parseLocalDateTime(jState, "dateSent", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime dateReceived = JsonIO.parseLocalDateTime(jState, "dateReceived", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime dateCanceled = JsonIO.parseLocalDateTime(jState, "dateCanceled", DateTimeFormatter.ISO_DATE_TIME);
		String cancellationReason = JsonIO.parseString(jState, "reason");
		Integer mergedOrderId = JsonIO.parseInteger(jState, "wasMergedInto");

		ShippingMethod shippingMethod = JsonIO.getGson().fromJson(jObject.get("shippingMethod"), ShippingMethod.class);
		String trackingNumber = JsonIO.parseString(jObject, "trackingNumber");
		boolean isPresale = JsonIO.parseBoolean(jObject, "isPresale");
		Address shippingAddress = JsonIO.getGson().fromJson(jObject.get("shippingAddress"), Address.class);
		int amountItems = JsonIO.parseInteger(jObject, "articleCount");
		String note = JsonIO.parseString(jObject, "note");
		Evaluation evaluation = JsonIO.getGson().fromJson(jObject.get("evaluation"), Evaluation.class);
		List<Article> listArticles = new ArrayList<>();
		for (JsonElement jEle : jObject.get("article").getAsJsonArray()) {
			listArticles.add(JsonIO.getGson().fromJson(jEle, Article.class));
		}
		BigDecimal listArticlesValue = JsonIO.parseBigDecimal(jObject, "articleValue");
		BigDecimal serviceFeeValue = JsonIO.parseBigDecimal(jObject, "serviceFeeValue");
		BigDecimal totalValue = JsonIO.parseBigDecimal(jObject, "totalValue");

		return new Order(orderId, isBuyer, seller, buyer, orderState, dateBought, datePaid, dateSent, dateReceived,
				dateCanceled, cancellationReason, mergedOrderId, shippingMethod, trackingNumber, isPresale,
				shippingAddress, amountItems, note, evaluation, listArticles, listArticlesValue, serviceFeeValue,
				totalValue);
	}

}
