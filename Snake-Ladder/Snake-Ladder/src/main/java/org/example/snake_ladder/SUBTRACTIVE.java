package org.example.snake_ladder;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SUBTRACTIVE {
    SNAKE("SNAKE");
    private  String value;
    SUBTRACTIVE(String value){
        this.value = value;

    }

    @JsonValue
    public String getName(){
        return value;
    }
}
