package com.lvdreamer.behavior.strategy.demo;

public enum SeriesDataTypeEnum {
    DEFAULT("default"),
    TOPN("topn"),
    BOOL("bool");
    private String value;

    SeriesDataTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
