package de.ics.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.ics.cardmarket4j.entity.Evaluation;
import de.ics.cardmarket4j.entity.enumeration.Complaint;
import de.ics.cardmarket4j.entity.enumeration.EvaluationGrade;
import de.ics.cardmarket4j.util.JsonIO;

public class EvaluationDeserializer implements JsonDeserializer<Evaluation> {

	@Override
	public Evaluation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();

		EvaluationGrade totalGrade = EvaluationGrade.parseId(JsonIO.parseInteger(jObject, "evaluationGrade"));
		EvaluationGrade itemGrade = EvaluationGrade.parseId(JsonIO.parseInteger(jObject, "itemDescription"));
		EvaluationGrade packagingGrade = EvaluationGrade.parseId(JsonIO.parseInteger(jObject, "packaging"));
		String comment = JsonIO.parseString(jObject, "comment");
		Set<Complaint> setComplaints = new HashSet<>();
		try {
			for (JsonElement jEle : jObject.get("complaint").getAsJsonArray()) {
				setComplaints.add(Complaint.parseId(jEle.getAsString()));
			}
		} catch (NullPointerException e) {

		}

		return new Evaluation(totalGrade, itemGrade, packagingGrade, comment, setComplaints);
	}

}
