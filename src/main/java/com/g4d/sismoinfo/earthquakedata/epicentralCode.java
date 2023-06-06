package com.g4d.sismoinfo.earthquakedata;

public enum epicentralCode {
    A("SURE"),
    B("ASSEZ SURE"),
    C("INCERTAINE"),
    D(""),
    E("ARBITRAIRE"),
    I("INFORMATION ISOLÉE"),
    K("ASSEZ SURE (SPONHEUER)");

    private String value;

    epicentralCode(String epicentralCode) {
        this.value = epicentralCode;
    }

    public String getValue() {
        return value;
    }
}
