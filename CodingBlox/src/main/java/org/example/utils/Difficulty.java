package org.example.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Difficulty {
    LOW(20),
    MEDIUM(50),
    HARD(100);
    private int payload;
    Difficulty(int payload){
        this.payload = payload;
    }

    @JsonValue
    public int getPayload(){
        return payload;
    }
}
