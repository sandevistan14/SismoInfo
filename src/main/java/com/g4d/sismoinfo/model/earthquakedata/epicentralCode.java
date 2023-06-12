package com.g4d.sismoinfo.model.earthquakedata;

/**
 * The `epicentralCode` enum represents the quality codes for epicentral intensity.
 */
public enum epicentralCode {
    A("SURE"),
    B("ASSEZ SURE"),
    C("INCERTAINE"),
    D(""),
    E("ARBITRAIRE"),
    I("INFORMATION ISOLÉE"),
    K("ASSEZ SURE (SPONHEUER)");

    private final String value;

    /**
     * Constructs a `epicentralCode` enum value with the given code.
     *
     * @param epicentralCode The code value for the epicentral intensity quality.
     */
    epicentralCode(String epicentralCode) {
        this.value = epicentralCode;
    }

    /**
     * Gets the string value of the epicentral intensity quality code.
     *
     * @return The string value of the epicentral intensity quality code.
     */
    public String getValue() {
        return value;
    }

    /**
     * Retrieves the `epicentralCode` enum value based on the given code value.
     *
     * @param givenName The code value for the epicentral intensity quality.
     * @return The corresponding `epicentralCode` enum value, or `null` if not found.
     */
    public static epicentralCode fromValue(String givenName) {
        for (epicentralCode code : values()) {
            if (code.value.equals(givenName)) {
                return code;
            }
        }
        return null;
    }
}
