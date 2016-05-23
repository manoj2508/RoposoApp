package com.example.manoj.roposoapp.model;

/**
 * Created by manoj on 23/05/16.
 */
public enum CardDataType {
    USER("user"),
    STORY("story");

    private String type;

    CardDataType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    public static CardDataType fromString(String text) {
        if (text != null) {
            for (CardDataType dataType : CardDataType.values()) {
                if (text.equalsIgnoreCase(dataType.type)) {
                    return dataType;
                }
            }
        }
        return null;
    }
}
