package com.g4d.sismoinfo.model.earthquakedata;

/**
 * The `Shock` enum represents the types of shocks associated with an earthquake.
 */
public enum Shock {
    BLANK(""),
    P("PRECURSEUR"),
    R("REPLIQUE"),
    E("SECOUSSE INDIVIDUALISEE D UN ESSAIM"),
    Z("GROUPE DE SECOUSSES D UN ESSAIM");

    private final String value;

    /**
     * Constructs a `Shock` enum value with the given shock type.
     *
     * @param shock The shock type.
     */
    Shock(String shock) {
        this.value = shock;
    }

    /**
     * Gets the string value of the shock type.
     *
     * @return The string value of the shock type.
     */
    public String getValue() {
        return value;
    }

    /**
     * Retrieves the `Shock` enum value based on the given shock type value.
     *
     * @param givenName The shock type value.
     * @return The corresponding `Shock` enum value, or `null` if not found.
     */
    public static Shock fromValue(String givenName) {
        for (Shock shock : values()) {
            if (shock.value.equals(givenName)) {
                return shock;
            }
        }
        return null;
    }
}
