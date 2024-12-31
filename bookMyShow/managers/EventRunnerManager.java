package com.avinash.lld.bookMyShow.managers;

import com.avinash.lld.bookMyShow.exception.SeatNotFoundException;
import com.avinash.lld.bookMyShow.exception.ShowNotFoundException;
import com.avinash.lld.bookMyShow.models.Seat;
import com.avinash.lld.bookMyShow.models.Show;
import com.avinash.lld.bookMyShow.models.ShowRunner;
import com.avinash.lld.bookMyShow.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * EventRunnerManager manages all operations related to ShowRunners, Shows, and Seats.
 * It handles the addition, retrieval, and updates for these entities and ensures data integrity.
 */
@Service
public class EventRunnerManager implements EventRunnerActions {

    // Map to store ShowRunner's Shows. Key: ShowRunner ID, Value: List of Shows
    private final Map<String, List<Show>> showRunnerShowsMap = new ConcurrentHashMap<>();

    // Map to store Shows' Seats. Key: Show ID, Value: List of Seats
    private final Map<String, List<Seat>> showSeatMap = new ConcurrentHashMap<>();

    /**
     * Adds a new ShowRunner to the system.
     *
     * @param showRunner the ShowRunner details
     * @return the added ShowRunner with an assigned unique ID
     * @throws IllegalArgumentException if the provided ShowRunner is null
     */
    @Override
    public ShowRunner addShowRunner(ShowRunner showRunner) {
        if (Objects.isNull(showRunner)) {
            throw new IllegalArgumentException("Invalid Show Runner");
        }
        showRunner.setId(Utils.getRandomID());
        showRunnerShowsMap.put(showRunner.getId(), new ArrayList<>());
        return showRunner;
    }

    /**
     * Adds a new Show under a specific ShowRunner.
     *
     * @param showRunnerId the ID of the ShowRunner
     * @param details      the Show details
     * @return the added Show with an assigned unique ID
     * @throws ShowNotFoundException if the specified ShowRunner ID does not exist
     */
    @Override
    public Show addShow(String showRunnerId, Show details) {
        if (!showRunnerShowsMap.containsKey(showRunnerId)) {
            throw new ShowNotFoundException("Show Runner not found: " + showRunnerId);
        }
        details.setId(Utils.getRandomID());
        showRunnerShowsMap.get(showRunnerId).add(details);
        showSeatMap.put(details.getId(), new ArrayList<>());
        return details;
    }

    /**
     * Adds a new Seat to a specific Show.
     *
     * @param showRunnerId the ID of the ShowRunner
     * @param showId       the ID of the Show
     * @param seat         the Seat details
     * @return the added Seat with an assigned unique ID and initial status
     * @throws ShowNotFoundException if the specified ShowRunner or Show ID does not exist
     */
    @Override
    public Seat addSeat(String showRunnerId, String showId, Seat seat) {
        if (!showRunnerShowsMap.containsKey(showRunnerId)) {
            throw new ShowNotFoundException("Show Runner not found: " + showRunnerId);
        }
        if (!showSeatMap.containsKey(showId)) {
            throw new ShowNotFoundException("Show not found: " + showId);
        }
        seat.setId(Utils.getRandomID());
        seat.setStatus(Seat.SeatStatus.AVAILABLE);
        seat.setVersion("V1");
        showSeatMap.get(showId).add(seat);
        return seat;
    }

    /**
     * Checks if a Show exists in the system.
     *
     * @param showId the ID of the Show
     * @return true if the Show exists, false otherwise
     */
    @Override
    public boolean checkShow(String showId) {
        return showSeatMap.containsKey(showId);
    }

    /**
     * Checks if a Seat exists in a specific Show.
     *
     * @param showId the ID of the Show
     * @param seatId the ID of the Seat
     * @return true if the Seat exists, false otherwise
     */
    @Override
    public boolean checkSeat(String showId, String seatId) {
        return showSeatMap.get(showId).stream().anyMatch(seat -> seat.getId().equals(seatId));
    }

    /**
     * Updates the status of a specific Seat in a Show.
     *
     * @param showId the ID of the Show
     * @param seatId the ID of the Seat
     * @param status the new status to be set
     * @return true if the Seat status is successfully updated, false otherwise
     * @throws ShowNotFoundException if the specified Show ID does not exist
     * @throws SeatNotFoundException if the specified Seat ID does not exist
     */
    @Override
    public boolean updateSeat(String showId, String seatId, Seat.SeatStatus status) {
        List<Seat> seats = showSeatMap.get(showId);
        if (seats == null) {
            throw new ShowNotFoundException("Show not found: " + showId);
        }
        for (Seat seat : seats) {
            if (seat.getId().equals(seatId)) {
                seat.setStatus(status);
                return true;
            }
        }
        throw new SeatNotFoundException("Seat not found: " + seatId);
    }

    /**
     * Retrieves a specific Show under a ShowRunner.
     *
     * @param showRunnerId the ID of the ShowRunner
     * @param showId       the ID of the Show
     * @return an Optional containing the Show if found, or empty otherwise
     */
    @Override
    public Optional<Show> getShow(String showRunnerId, String showId) {
        return showRunnerShowsMap.getOrDefault(showRunnerId, Collections.emptyList())
                .stream().filter(show -> show.getId().equals(showId)).findFirst();
    }
}
