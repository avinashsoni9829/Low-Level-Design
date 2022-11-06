package org.example.Machine;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {
     CREDIT("CREDIT"),
     DEBIT("DEBIT"),
    FAILED("FAILED");


    private String val;
    TransactionType(String val){
        this.val = val;
    }

    @JsonValue
    public String getVal(){
        return val;
    }
}
