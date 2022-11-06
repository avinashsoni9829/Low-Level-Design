package org.example.Machine;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Verdict {
    SUCCESS("SUCCESS"),
    FAILURE("FAILURE"),
    OUT_OF_MONEY("OUT OF MONEY"),
    HARDWARE_ERROR("HARDWARE ERROR"),
    SOFTWARE_ERROR("SOFTWARE_ERROR");

    private String val;
    Verdict(String val){
        this.val = val;
    }

    @JsonValue
    public String getVal(){
        return val;
    }

}
