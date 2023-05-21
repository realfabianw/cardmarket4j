package de.cardmarket4j.entity.enumeration;

public enum ItemType {

    PRODUCT("product"),
    METAPRODUCT("metaproduct");

    private final String value;

    ItemType(String value) {
        this.value = value;
    }

    public static ItemType parseValue(String value) {
        for (ItemType e : ItemType.values()) {
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
