package de.cardmarket4j.entity.util;

import com.neovisionaries.i18n.LanguageCode;

import de.cardmarket4j.entity.enumeration.Game;
import de.cardmarket4j.util.CardMarketUtils;

/**
 * This class represents the filter function for products on cardmarket. Filters
 * are only activated if they are NOT NULL.
 * 
 * @author QUE
 *
 */
public class ProductFilter {
	private final String searchQuery;
	private boolean exact;
	private Game game;
	private LanguageCode language;
	private Integer start;
	private Integer maxResults;

	public ProductFilter(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductFilter other = (ProductFilter) obj;
		if (exact != other.exact)
			return false;
		if (game != other.game)
			return false;
		if (language != other.language)
			return false;
		if (maxResults == null) {
			if (other.maxResults != null)
				return false;
		} else if (!maxResults.equals(other.maxResults))
			return false;
		if (searchQuery == null) {
			if (other.searchQuery != null)
				return false;
		} else if (!searchQuery.equals(other.searchQuery))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	public Game getGame() {
		return game;
	}

	public LanguageCode getLanguage() {
		return language;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public String getQuery() {
		StringBuilder sb = new StringBuilder("search=" + searchQuery);
		if (exact) {
			sb.append("&exact=true");
		}
		if (game != null) {
			sb.append("&idGame=" + game.getId());
		}
		if (language != null) {
			sb.append("&idLanguage=" + CardMarketUtils.toLanguageId(language));
		}
		if (start != null || maxResults != null) {
			sb.append("&start=" + (start != null ? start : 0));
			sb.append("&maxResults=" + (maxResults != null ? maxResults : 100));
		}
		return sb.toString();
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public int getStart() {
		return start;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (exact ? 1231 : 1237);
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((maxResults == null) ? 0 : maxResults.hashCode());
		result = prime * result + ((searchQuery == null) ? 0 : searchQuery.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	public boolean isExact() {
		return exact;
	}

	public void setExact(boolean exact) {
		this.exact = exact;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setLanguage(LanguageCode language) {
		this.language = language;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	@Override
	public String toString() {
		return "ProductFilter [" + (searchQuery != null ? "searchQuery=" + searchQuery + ", " : "") + "exact=" + exact
				+ ", " + (game != null ? "game=" + game + ", " : "")
				+ (language != null ? "language=" + language + ", " : "")
				+ (start != null ? "start=" + start + ", " : "")
				+ (maxResults != null ? "maxResults=" + maxResults : "") + "]";
	}
}
