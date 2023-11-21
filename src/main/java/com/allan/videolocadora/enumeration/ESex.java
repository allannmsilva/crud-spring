package com.allan.videolocadora.enumeration;

public enum ESex {
    MALE("Male"), FEMALE("Female"), UNDEFINED("Undefined");

    private final String value;

    private ESex(String value) {
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
