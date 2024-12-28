package com.management.library.enums;

public enum Prefix {
    ROLE("RO"),
    USER("US")
    ;
    private final String prefix;


    Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
