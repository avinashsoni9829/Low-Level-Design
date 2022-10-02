package org.example.gameManagement;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GameState {
    START("Start"),
    ONGOING("Ongoing"),
    END("End");
    private String value;

    GameState(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue(){
        return value;
    }
}
