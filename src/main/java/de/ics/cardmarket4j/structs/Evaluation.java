package de.ics.cardmarket4j.structs;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonIO;
import de.ics.cardmarket4j.enums.Complaint;
import de.ics.cardmarket4j.enums.EvaluationGrade;

/**
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Evaluation
 * @author QUE
 *
 */
public class Evaluation {
	private String jsonString;
	private EvaluationGrade totalGrade;
	private EvaluationGrade itemGrade;
	private EvaluationGrade packagingGrade;
	private String comment;
	private Set<Complaint> setComplaints;

	public Evaluation(JsonObject jObject) {
		this.jsonString = jObject.toString();
		this.totalGrade = EvaluationGrade.parseId(JsonIO.parseInteger(jObject, "evaluationGrade"));
		this.itemGrade = EvaluationGrade.parseId(JsonIO.parseInteger(jObject, "itemDescription"));
		this.packagingGrade = EvaluationGrade.parseId(JsonIO.parseInteger(jObject, "packaging"));
		this.comment = JsonIO.parseString(jObject, "comment");
		this.setComplaints = new HashSet<>();
		try {
			for (JsonElement jEle : jObject.get("complaint").getAsJsonArray()) {
				setComplaints.add(Complaint.parseId(jEle.getAsString()));
			}
		} catch (NullPointerException e) {

		}
	}

	public Evaluation(String jsonString, EvaluationGrade totalGrade, EvaluationGrade itemGrade,
			EvaluationGrade packagingGrade, String comment, Set<Complaint> setComplaints) {
		this.jsonString = jsonString;
		this.totalGrade = totalGrade;
		this.itemGrade = itemGrade;
		this.packagingGrade = packagingGrade;
		this.comment = comment;
		this.setComplaints = setComplaints;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evaluation other = (Evaluation) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (itemGrade != other.itemGrade)
			return false;
		if (jsonString == null) {
			if (other.jsonString != null)
				return false;
		} else if (!jsonString.equals(other.jsonString))
			return false;
		if (packagingGrade != other.packagingGrade)
			return false;
		if (setComplaints == null) {
			if (other.setComplaints != null)
				return false;
		} else if (!setComplaints.equals(other.setComplaints))
			return false;
		if (totalGrade != other.totalGrade)
			return false;
		return true;
	}

	public String getComment() {
		return comment;
	}

	public EvaluationGrade getItemGrade() {
		return itemGrade;
	}

	public String getJsonString() {
		return jsonString;
	}

	public EvaluationGrade getPackagingGrade() {
		return packagingGrade;
	}

	public Set<Complaint> getSetComplaints() {
		return setComplaints;
	}

	public EvaluationGrade getTotalGrade() {
		return totalGrade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((itemGrade == null) ? 0 : itemGrade.hashCode());
		result = prime * result + ((jsonString == null) ? 0 : jsonString.hashCode());
		result = prime * result + ((packagingGrade == null) ? 0 : packagingGrade.hashCode());
		result = prime * result + ((setComplaints == null) ? 0 : setComplaints.hashCode());
		result = prime * result + ((totalGrade == null) ? 0 : totalGrade.hashCode());
		return result;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setItemGrade(EvaluationGrade itemGrade) {
		this.itemGrade = itemGrade;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public void setPackagingGrade(EvaluationGrade packagingGrade) {
		this.packagingGrade = packagingGrade;
	}

	public void setSetComplaints(Set<Complaint> setComplaints) {
		this.setComplaints = setComplaints;
	}

	public void setTotalGrade(EvaluationGrade totalGrade) {
		this.totalGrade = totalGrade;
	}

	@Override
	public String toString() {
		return "Evaluation [jsonString=" + jsonString + ", totalGrade=" + totalGrade + ", itemGrade=" + itemGrade
				+ ", packagingGrade=" + packagingGrade + ", comment=" + comment + ", setComplaints=" + setComplaints
				+ "]";
	}

}
