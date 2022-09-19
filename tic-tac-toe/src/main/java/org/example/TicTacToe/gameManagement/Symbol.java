package org.example.TicTacToe.gameManagement;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Symbol {
    START('*'),
    ZERO('O'),
    CROSS('X');
    private Character val;

    Symbol(Character val){
        this.val = val;

    }

    @JsonValue
    public Character getValue() {
        return val;
    }
}
