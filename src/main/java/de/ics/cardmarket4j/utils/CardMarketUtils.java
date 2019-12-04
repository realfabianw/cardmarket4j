package de.ics.cardmarket4j.utils;

import com.neovisionaries.i18n.LanguageCode;

public class CardMarketUtils {

	public static LanguageCode fromLanguageId(int id) {
		if (id == 1) {
			return LanguageCode.en;
		}
		if (id == 2) {
			return LanguageCode.fr;
		}
		if (id == 3) {
			return LanguageCode.de;
		}
		if (id == 4) {
			return LanguageCode.es;
		}
		if (id == 5) {
			return LanguageCode.it;
		}
		if (id == 6) {
			return (LanguageCode.getByCode("zh-hans", false) != null ? LanguageCode.getByCode("zh-hans", false)
					: LanguageCode.zh);
		}
		if (id == 7) {
			return LanguageCode.ja;
		}
		if (id == 8) {
			return LanguageCode.pt;
		}
		if (id == 9) {
			return LanguageCode.ru;
		}
		if (id == 10) {
			return LanguageCode.ko;
		}
		if (id == 11) {
			return (LanguageCode.getByCode("zh-hant", false) != null ? LanguageCode.getByCode("zh-hans", false)
					: LanguageCode.zh);
		}
		throw new IllegalArgumentException("languageId " + id + " has no entry");
	}

	public static int toLanguageId(LanguageCode code) {
		switch (code) {
		case en:
			return 1;
		case fr:
			return 2;
		case de:
			return 3;
		case es:
			return 4;
		case it:
			return 5;
		case zh:
			return 6;
		case ja:
			return 7;
		case pt:
			return 8;
		case ru:
			return 9;
		case ko:
			return 10;
		default:
			throw new IllegalArgumentException("LanguageCode " + code + " has no entry");
		}
	}
}
