package de.ics.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.neovisionaries.i18n.LanguageCode;

import de.ics.cardmarket4j.entity.Article;
import de.ics.cardmarket4j.entity.Product;
import de.ics.cardmarket4j.entity.User;
import de.ics.cardmarket4j.entity.enumeration.Condition;
import de.ics.cardmarket4j.utils.CardMarketUtils;
import de.ics.cardmarket4j.utils.JsonIO;

public class ArticleDeserializer implements JsonDeserializer<Article> {

	@Override
	public Article deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();

		int articleId = JsonIO.parseInteger(jObject, "idArticle");
		int productId = JsonIO.parseInteger(jObject, "idProduct");
		LanguageCode languageCode = null;
		try {
			languageCode = CardMarketUtils
					.fromLanguageId(JsonIO.parseInteger(jObject.get("language").getAsJsonObject(), "idLanguage"));
		} catch (NullPointerException e) {
			// Some API-Calls dont return this (Successfull Deletion of Article - Return)
		}
		String comment = JsonIO.parseString(jObject, "comments");
		BigDecimal price = JsonIO.parseBigDecimal(jObject, "price");
		int quantity = JsonIO.parseInteger(jObject, "count");
		boolean inShoppingCart = JsonIO.parseBoolean(jObject, "inShoppingCart");
		Product product = JsonIO.getGson().fromJson(jObject.get("product"), Product.class);
		User seller = JsonIO.getGson().fromJson(jObject.get("seller"), User.class);
		LocalDateTime lastEdited = JsonIO.parseLocalDateTime(jObject, "lastEdited", DateTimeFormatter.ISO_DATE_TIME);
		Condition condition = Condition.parseId(JsonIO.parseString(jObject, "condition"));

		boolean foil = JsonIO.parseBoolean(jObject, "isFoil");
		boolean signed = JsonIO.parseBoolean(jObject, "isSigned");
		boolean altered = JsonIO.parseBoolean(jObject, "isAltered");
		boolean playset = JsonIO.parseBoolean(jObject, "isPlayset");
		boolean firstEdition = JsonIO.parseBoolean(jObject, "isFirstEd");

		return new Article(articleId, productId, languageCode, comment, price, quantity, inShoppingCart, product,
				seller, lastEdited, condition, foil, signed, altered, playset, firstEdition);
	}

}
