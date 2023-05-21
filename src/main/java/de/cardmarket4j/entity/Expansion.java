package de.cardmarket4j.entity;

import java.time.LocalDateTime;
import java.util.Map;

import com.neovisionaries.i18n.LanguageCode;

import de.cardmarket4j.entity.enumeration.Game;

/**
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Expansion
 * @author QUE
 *
 */
public class Expansion {
	private int expansionId;
	private String name;
	private Map<LanguageCode, String> mapLocalizedNames;
	private String code;
	private Integer iconCode;
	private LocalDateTime releaseDate;
	private Game game;

	public Expansion(int expansionId, String name, Map<LanguageCode, String> mapLocalizedNames, String code,
			Integer iconCode, LocalDateTime releaseDate, Game game) {
		this.expansionId = expansionId;
		this.name = name;
		this.mapLocalizedNames = mapLocalizedNames;
		this.code = code;
		this.iconCode = iconCode;
		this.releaseDate = releaseDate;
		this.game = game;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expansion other = (Expansion) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (expansionId != other.expansionId)
			return false;
		if (game != other.game)
			return false;
		if (iconCode == null) {
			if (other.iconCode != null)
				return false;
		} else if (!iconCode.equals(other.iconCode))
			return false;
		if (mapLocalizedNames == null) {
			if (other.mapLocalizedNames != null)
				return false;
		} else if (!mapLocalizedNames.equals(other.mapLocalizedNames))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (releaseDate == null) {
			return other.releaseDate == null;
		} else return releaseDate.equals(other.releaseDate);
	}

	public String getCode() {
		return code;
	}

	public int getExpansionId() {
		return expansionId;
	}

	public Game getGame() {
		return game;
	}

	public Integer getIconCode() {
		return iconCode;
	}

	public Map<LanguageCode, String> getMapLocalizedNames() {
		return mapLocalizedNames;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getReleaseDate() {
		return releaseDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + expansionId;
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((iconCode == null) ? 0 : iconCode.hashCode());
		result = prime * result + ((mapLocalizedNames == null) ? 0 : mapLocalizedNames.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		return result;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setExpansionId(int expansionId) {
		this.expansionId = expansionId;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setIconCode(Integer iconCode) {
		this.iconCode = iconCode;
	}

	public void setMapLocalizedNames(Map<LanguageCode, String> mapLocalizedNames) {
		this.mapLocalizedNames = mapLocalizedNames;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReleaseDate(LocalDateTime releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Expansion [expansionId=" + expansionId + ", " + (name != null ? "name=" + name + ", " : "")
				+ (mapLocalizedNames != null ? "mapLocalizedNames=" + mapLocalizedNames + ", " : "")
				+ (code != null ? "code=" + code + ", " : "") + (iconCode != null ? "iconCode=" + iconCode + ", " : "")
				+ (releaseDate != null ? "releaseDate=" + releaseDate + ", " : "")
				+ (game != null ? "game=" + game : "") + "]";
	}
}
