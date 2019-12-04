package de.ics.cardmarket4j.structs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.LanguageCode;

import de.ics.cardmarket4j.JsonIO;
import de.ics.cardmarket4j.enums.Game;
import de.ics.cardmarket4j.utils.CardMarketUtils;

/**
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Expansion
 * @author QUE
 *
 */
public class Expansion {
	private String jsonString;
	private int expansionId;
	private String name;
	private Map<LanguageCode, String> mapLocalizedNames;
	private String code;
	private int iconCode;
	private LocalDateTime releaseDate;
	private Game game;

	public Expansion(JsonObject jObject) {
		this.jsonString = jObject.toString();
		this.expansionId = JsonIO.parseInteger(jObject, "idExpansion");
		this.name = JsonIO.parseString(jObject, "enName");
		this.mapLocalizedNames = new HashMap<>();
		for (JsonElement jElement : jObject.get("localization").getAsJsonArray()) {
			JsonObject jLoc = jElement.getAsJsonObject();
			mapLocalizedNames.put(CardMarketUtils.fromLanguageId(JsonIO.parseInteger(jLoc, "idLanguage")),
					JsonIO.parseString(jLoc, "name"));
		}
		this.code = JsonIO.parseString(jObject, "abbreviation");
		this.iconCode = JsonIO.parseInteger(jObject, "icon");
		this.releaseDate = JsonIO.parseLocalDateTime(jObject, "releaseDate", DateTimeFormatter.ISO_DATE_TIME);
		this.game = Game.parseId(JsonIO.parseInteger(jObject, "idGame"));
	}

	public Expansion(String jsonString, int expansionId, String name, Map<LanguageCode, String> mapLocalizedNames,
			String code, int iconCode, LocalDateTime releaseDate, Game game) {
		this.jsonString = jsonString;
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
		if (iconCode != other.iconCode)
			return false;
		if (jsonString == null) {
			if (other.jsonString != null)
				return false;
		} else if (!jsonString.equals(other.jsonString))
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
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		return true;
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

	public int getIconCode() {
		return iconCode;
	}

	public String getJsonString() {
		return jsonString;
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
		result = prime * result + iconCode;
		result = prime * result + ((jsonString == null) ? 0 : jsonString.hashCode());
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

	public void setIconCode(int iconCode) {
		this.iconCode = iconCode;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
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
		return "Expansion [jsonString=" + jsonString + ", expansionId=" + expansionId + ", name=" + name
				+ ", mapLocalizedNames=" + mapLocalizedNames + ", code=" + code + ", iconCode=" + iconCode
				+ ", releaseDate=" + releaseDate + ", game=" + game + "]";
	}
}
