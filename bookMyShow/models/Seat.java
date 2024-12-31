package com.avinash.lld.bookMyShow.models;

import lombok.Data;

@Data
public class Seat {
    private String id;
    private String version;
    private SeatStatus status;

    public enum SeatStatus {
        AVAILABLE,
        TEMPORARILY_BLOCKED,
        IN_BOOKING,
        BOOKED
    }
}