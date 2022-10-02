package org.example.snake_ladder;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ADDITIVE {
    LADDER("LADDER");

    private  String value;
    ADDITIVE(String value){
        this.value = value;

    }

    @JsonValue
    public String getName(){
        return value;
    }
}
