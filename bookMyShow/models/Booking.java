package com.avinash.lld.bookMyShow.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Booking {
    private String id;
    private LocalDateTime txnDateTime;
    private Show show;
    private List<Seat> seatsBooked;
    private BookingStatus status;

    public enum BookingStatus {
        COMPLETED,
        FAILED
    }
}