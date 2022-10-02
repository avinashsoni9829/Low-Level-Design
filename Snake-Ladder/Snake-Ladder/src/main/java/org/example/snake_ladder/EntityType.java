package org.example.snake_ladder;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EntityType {
    SMALL(0.2),
    MEDIUM(0.4),
    LARGE(0.6),
    SUPER(0.8);

    private  Double value;
    EntityType(Double value){
        this.value = value;
    }

    @JsonValue
    public Double getValue(){
        return value;
    }
}
