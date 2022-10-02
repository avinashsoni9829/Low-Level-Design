package org.example.snake_ladder;

import com.fasterxml.jackson.annotation.JsonValue;


public enum Cell {

    ADD("ADDITIVE"),
    SUB("SUBTRACTIVE"),
    START("start"),
    END("end"),
    NORMAL("normal");

    private  String value;
    Cell(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return value;
    }
}
