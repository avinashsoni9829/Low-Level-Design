package com.avinash.lld.bookMyShow.managers;

import com.avinash.lld.bookMyShow.models.Seat;
import com.avinash.lld.bookMyShow.models.Show;
import com.avinash.lld.bookMyShow.models.ShowRunner;

import java.util.Optional;

public interface EventRunnerActions {
     ShowRunner addShowRunner(ShowRunner showRunner);
     Show addShow(String showRunnerId, Show details);
     Seat addSeat(String showRunnerId, String showId, Seat seat);
     boolean checkShow(String showId);
     boolean checkSeat(String showId, String seatId);
     boolean updateSeat(String showId, String seatId, Seat.SeatStatus status);
     Optional<Show> getShow(String showRunnerId, String showId);
}