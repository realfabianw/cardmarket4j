package de.cardmarket4j.entity;

import java.util.Set;

import de.cardmarket4j.entity.enumeration.Complaint;
import de.cardmarket4j.entity.enumeration.EvaluationGrade;

/**
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Evaluation
 * @author QUE
 *
 */
public class Evaluation {
	private EvaluationGrade totalGrade;
	private EvaluationGrade itemGrade;
	private EvaluationGrade packagingGrade;
	private String comment;
	private Set<Complaint> setComplaints;

	public Evaluation(EvaluationGrade totalGrade, EvaluationGrade itemGrade, EvaluationGrade packagingGrade,
			String comment, Set<Complaint> setComplaints) {
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
		if (packagingGrade != other.packagingGrade)
			return false;
		if (setComplaints == null) {
			if (other.setComplaints != null)
				return false;
		} else if (!setComplaints.equals(other.setComplaints))
			return false;
		return totalGrade == other.totalGrade;
	}

	public String getComment() {
		return comment;
	}

	public EvaluationGrade getItemGrade() {
		return itemGrade;
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
		return "Evaluation [" + (totalGrade != null ? "totalGrade=" + totalGrade + ", " : "")
				+ (itemGrade != null ? "itemGrade=" + itemGrade + ", " : "")
				+ (packagingGrade != null ? "packagingGrade=" + packagingGrade + ", " : "")
				+ (comment != null ? "comment=" + comment + ", " : "")
				+ (setComplaints != null ? "setComplaints=" + setComplaints : "") + "]";
	}

}
