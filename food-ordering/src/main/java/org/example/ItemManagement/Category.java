package org.example.ItemManagement;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Category {
    CHINESE("CHINESE"),
    SOUTH_INDIAN("SOUTH_INDIAN"),
    NORTH_INDIAN("NORTH_INDIAN"),
    PURE_VEG("PURE_VEG"),
    NON_VEG("NON_VEG"),
    PIZZA("PIZZA"),
    PASTA("PASTA"),
    COFFEE("COFFEE"),
    SANDWICH("SANDWICH");

    private String val;
    Category(String val){
        this.val = val;
    }

    @JsonValue
    public String getVal(){
        return val;
    }
}
