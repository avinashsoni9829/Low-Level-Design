package com.avinash.lld.bookMyShow.managers;

import com.avinash.lld.bookMyShow.exception.BookingException;
import com.avinash.lld.bookMyShow.models.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BookingManager handles the logic for booking seats in a Show.
 * It ensures concurrency handling and maintains seat status integrity.
 */
@Service
public class BookingManager implements BookingManagerActions {

    @Autowired
    private EventRunnerManager eventRunnerManager;

    /**
     * Creates a booking for a specific seat in a Show.
     * This method handles concurrency using a synchronized block on the Seat object.
     *
     * @param showId  the ID of the Show
     * @param seat    the Seat to be booked
     * @param version the current version of the Seat
     * @return the new status of the Seat after the booking operation
     * @throws BookingException if the Show is not found or if the seat version does not match
     */
    @Override
    public Seat.SeatStatus createBooking(String showId, Seat seat, String version) {
        // Synchronize on the seat object to handle concurrent booking attempts
        synchronized (seat) {
            // Check if the Show exists
            if (!eventRunnerManager.checkShow(showId)) {
                throw new BookingException("Show not found: " + showId);
            }

            // Check if the seat version matches the provided version
            if (!seat.getVersion().equals(version)) {
                // Temporarily block the seat if versions do not match
                eventRunnerManager.updateSeat(showId, seat.getId(), Seat.SeatStatus.TEMPORARILY_BLOCKED);
                return Seat.SeatStatus.TEMPORARILY_BLOCKED;
            }

            // Mark the seat as BOOKED
            eventRunnerManager.updateSeat(showId, seat.getId(), Seat.SeatStatus.BOOKED);

            // Update the version of the seat
            seat.setVersion("V" + (Integer.parseInt(version.substring(1)) + 1));
            return Seat.SeatStatus.BOOKED;
        }
    }
}
