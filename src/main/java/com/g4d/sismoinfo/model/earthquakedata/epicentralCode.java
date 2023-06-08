package com.g4d.sismoinfo.model.earthquakedata;

public enum epicentralCode {
    A("SURE"),
    B("ASSEZ SURE"),
    C("INCERTAINE"),
    D(""),
    E("ARBITRAIRE"),
    I("INFORMATION ISOLÉE"),
    K("ASSEZ SURE (SPONHEUER)");

    private final String value;

    epicentralCode(String epicentralCode) {
        this.value = epicentralCode;
    }

    public String getValue() {
        return value;
    }

    public static epicentralCode fromValue(String givenName) {
        for (epicentralCode code : values()) {
            if (code.value.equals(givenName)) {
                return code;
            }
        }
        return null;
    }
}
