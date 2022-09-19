package org.example.TicTacToe.userManagement;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum {

    MALE("M"),FEMALE("F") ;
    private String value;

    GenderEnum(String value){
         this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
