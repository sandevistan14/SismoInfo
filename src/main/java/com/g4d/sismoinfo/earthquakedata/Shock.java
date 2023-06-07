package com.g4d.sismoinfo.earthquakedata;

public enum Shock {
    BLANK(""),
    P("PRECURSEUR"),
    R("REPLIQUE"),
    E("SECOUSSE INDIVIDUALISEE D UN ESSAIM"),
    Z("GROUPE DE SECOUSSES D UN ESSAIM");

    private final String value;

    Shock(String shock) {
        this.value = shock;
    }

    public String getValue() {
        return value;
    }

    public static Shock fromValue(String givenName) {
        for (Shock shock : values()) {
            if (shock.value.equals(givenName)) {
                return shock;
            }
        }
        return null;
    }
}
