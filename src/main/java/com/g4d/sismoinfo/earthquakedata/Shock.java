package com.g4d.sismoinfo.earthquakedata;

public enum Shock {
    BLANK(" "),
    P("PRECURSEUR"),
    R("REPLIQUE"),
    E("SECOUSSE INDIVIDUALISEE D UN ESSAIM"),
    Z("GROUPE DE SECOUSSES D UN ESSAIM");

    private String value;

    Shock(String shock) {
        this.value = shock;
    }

    public String getValue() {
        return value;
    }
}
