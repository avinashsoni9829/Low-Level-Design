package org.example.TicTacToe.gameManagement;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EventType {
    WIN("Congratulations!"), DRAW("The Game is a Draw") , ONGOING("The Game is Still  On");

    private String val;
    EventType(String val){
         this.val = val;
    }

    @JsonValue
    public String getValue() {
        return val;
    }
}
