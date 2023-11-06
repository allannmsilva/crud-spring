package com.allan.videolocadora.enumeration;

import org.mapstruct.Mapper;

public enum ECategory {
    HORROR("Horror"), THRILLER("Thriller"),
    COMEDY("Comedy"), ROMANCE("Romance");

    private final String value;

    private ECategory(String value) {
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
