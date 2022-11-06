package org.example.Machine;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Condition {
    WORKING("WORKING"),
    TECHNICAL_ERROR("Technical-Error"),
    OUT_OF_BALANCE("Out of Balance"),
    ABANDONED("Abandoned");
    private String val;
    Condition(String val){
        this.val = val;
    }

    @JsonValue
    public String getVal(){
        return  val;
    }
}
