package com.allan.videolocadora.enumeration;

public enum EPaid {
    YES("Yes"), NO("No");

    private final String value;

    private EPaid(String value) {
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
