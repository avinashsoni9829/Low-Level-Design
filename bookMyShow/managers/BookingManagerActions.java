package com.avinash.lld.bookMyShow.managers;

import com.avinash.lld.bookMyShow.models.Seat;

public interface BookingManagerActions {
    Seat.SeatStatus createBooking(String showId, Seat seat, String version);
}
