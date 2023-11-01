package com.allan.videolocadora.enumeration;

public enum EItemType {
    TAPE("Tape"), DVD("DVD"), BLURAY("Blu-ray");

    private final String value;

    private EItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
