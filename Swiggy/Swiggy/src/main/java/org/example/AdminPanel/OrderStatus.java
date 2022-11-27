package org.example.AdminPanel;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    INITIATED_BY_USER("Initiated"),
    WAITING_FOR_ACCEPTANCE("Waiting"),
    ACCEPTED_BY_SELLER("Accepted"),
    COOKING("Cooking"),
    READY_FOR_DELIVERY("Ready For Delivery"),
    IN_TRANSIT("In Transit"),
    SUCCESSFULLY_DELIVERED("Successfully Delivered"),
    COOKING_TAKING_TIME("Taking Time"),
    DELAY("DELAY"),
    WEATHER_CONDITION("Weather Condition"),
    DELIVERY_TAKING_TIME("Delivery Taking Time"),
    CANCELLED_BY_SELLER("Cancelled By Seller"),
    CANCELLED_BY_USER("Cancelled By User"),
    REFUND_REQUESTED("Refund Requested"),
    REFUND_SUCCESS("Refund Success"),
    REFUND_UNSUCCESSFULL("Refund Unsuccessfull"),
    APP_OUTAGE("App Outage"),
    OTHER("Other");
    private String val;
    OrderStatus(String val){
        this.val =val;
    }


    @JsonValue
    public String getVal(){
        return val;
    }
}
