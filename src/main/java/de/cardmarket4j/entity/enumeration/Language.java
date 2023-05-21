package de.cardmarket4j.entity.enumeration;

public enum Language {
    ENGLISH("English"), FRENCH("French"), GERMAN("German"), ITALIAN("Italian"), SPANISH("Spanish"),
    PORTUGUESE("Portuguese"), RUSSIAN("Russian"), KOREAN("Korean"), CHINESE("Chinese"),
    ANCIENT_GREEK("Ancient Greek"), ARABIC("Arabic"), HEBREW("Hebrew"), LATIN("Latin"),
    SANSKRIT("Sanskrit");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public static Language parseValue(String value) {
        for (Language e : Language.values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Couldn't find an enum matching this value: " + value);
    }

    public String getValue() {
        return value;
    }
}
