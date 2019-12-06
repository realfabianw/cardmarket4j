package de.ics.cardmarket4j.entity.util;

import com.neovisionaries.i18n.LanguageCode;

import de.ics.cardmarket4j.entity.enumeration.Condition;
import de.ics.cardmarket4j.entity.enumeration.Reputation;
import de.ics.cardmarket4j.entity.enumeration.UserType;
import de.ics.cardmarket4j.util.CardMarketUtils;

/**
 * This class represents the filter function for articles on cardmarket. Filters
 * are only activated if they are NOT NULL.
 * 
 * @author QUE
 * @version 30.01.2019
 *
 */
public class ArticleFilter {
	private UserType userType;
	private Integer start;
	private Integer maxResults;
	private Reputation minReputation;
	private LanguageCode language;
	private Condition minCondition;
	private Boolean foil;
	private Boolean signed;
	private Boolean altered;
	private Integer minAvailable;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleFilter other = (ArticleFilter) obj;
		if (altered == null) {
			if (other.altered != null)
				return false;
		} else if (!altered.equals(other.altered))
			return false;
		if (foil == null) {
			if (other.foil != null)
				return false;
		} else if (!foil.equals(other.foil))
			return false;
		if (language != other.language)
			return false;
		if (maxResults == null) {
			if (other.maxResults != null)
				return false;
		} else if (!maxResults.equals(other.maxResults))
			return false;
		if (minAvailable == null) {
			if (other.minAvailable != null)
				return false;
		} else if (!minAvailable.equals(other.minAvailable))
			return false;
		if (minCondition != other.minCondition)
			return false;
		if (minReputation != other.minReputation)
			return false;
		if (signed == null) {
			if (other.signed != null)
				return false;
		} else if (!signed.equals(other.signed))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (userType != other.userType)
			return false;
		return true;
	}

	public Boolean getAltered() {
		return altered;
	}

	public Boolean getFoil() {
		return foil;
	}

	public LanguageCode getLanguage() {
		return language;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public Integer getMinAvailable() {
		return minAvailable;
	}

	public Condition getMinCondition() {
		return minCondition;
	}

	public Reputation getMinReputation() {
		return minReputation;
	}

	public String getQuery() {
		StringBuilder sb = new StringBuilder("?");
		if (start != null || maxResults != null) {
			if (sb.charAt(sb.length() - 1) != '?') {
				sb.append("&");
			}
			sb.append("start=" + (start != null ? start : 0));

			if (sb.charAt(sb.length() - 1) != '?') {
				sb.append("&");
			}
			sb.append("maxResults=" + (maxResults != null ? maxResults : 1000));
		}
		if (userType != null) {
			if (sb.charAt(sb.length() - 1) != '?') {
				sb.append("&");
			}
			sb.append("userType=" + userType.toString());
		}
		if (minReputation != null) {
			if (sb.charAt(sb.length() - 1) != '?') {
				sb.append("&");
			}
			sb.append("minUserScore=" + minReputation.getId());
		}
		if (language != null) {
			if (sb.charAt(sb.length() - 1) != '?') {
				sb.append("&");
			}
			sb.append("idLanguage=" + CardMarketUtils.toLanguageId(language));
		}
		if (minCondition != null) {
			if (sb.charAt(sb.length() - 1) != '?') {
				sb.append("&");
			}
			sb.append("minCondition=" + minCondition.getId());
		}
		if (foil != null) {
			if (sb.charAt(sb.length() - 1) != '?') {
				sb.append("&");
			}
			sb.append("isFoil=" + foil);
		}
		if (signed != null) {
			if (sb.charAt(sb.length() - 1) != '?') {
				sb.append("&");
			}
			sb.append("isSigned=" + signed);
		}
		if (altered != null) {
			if (sb.charAt(sb.length() - 1) != '?') {
				sb.append("&");
			}
			sb.append("isAltered=" + altered);
		}
		if (minAvailable != null) {
			if (sb.charAt(sb.length() - 1) != '?') {
				sb.append("&");
			}
			sb.append("minAvailable=" + minAvailable);
		}
		return sb.toString();
	}

	public Boolean getSigned() {
		return signed;
	}

	public Integer getStart() {
		return start;
	}

	public UserType getUserType() {
		return userType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altered == null) ? 0 : altered.hashCode());
		result = prime * result + ((foil == null) ? 0 : foil.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((maxResults == null) ? 0 : maxResults.hashCode());
		result = prime * result + ((minAvailable == null) ? 0 : minAvailable.hashCode());
		result = prime * result + ((minCondition == null) ? 0 : minCondition.hashCode());
		result = prime * result + ((minReputation == null) ? 0 : minReputation.hashCode());
		result = prime * result + ((signed == null) ? 0 : signed.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		return result;
	}

	public void setAltered(Boolean altered) {
		this.altered = altered;
	}

	public void setFoil(Boolean foil) {
		this.foil = foil;
	}

	public void setLanguage(LanguageCode language) {
		this.language = language;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public void setMinAvailable(Integer minAvailable) {
		this.minAvailable = minAvailable;
	}

	public void setMinCondition(Condition minCondition) {
		this.minCondition = minCondition;
	}

	public void setMinReputation(Reputation minReputation) {
		this.minReputation = minReputation;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "ArticleFilter [" + (userType != null ? "userType=" + userType + ", " : "")
				+ (start != null ? "start=" + start + ", " : "")
				+ (maxResults != null ? "maxResults=" + maxResults + ", " : "")
				+ (minReputation != null ? "minReputation=" + minReputation + ", " : "")
				+ (language != null ? "language=" + language + ", " : "")
				+ (minCondition != null ? "minCondition=" + minCondition + ", " : "")
				+ (foil != null ? "foil=" + foil + ", " : "") + (signed != null ? "signed=" + signed + ", " : "")
				+ (altered != null ? "altered=" + altered + ", " : "")
				+ (minAvailable != null ? "minAvailable=" + minAvailable : "") + "]";
	}
}
