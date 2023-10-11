package com.allan.videolocadora.enumeration;

public enum EStatus {
    ACTIVE("Active"), INACTIVE("Inactive");

    private final String value;

    private EStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
