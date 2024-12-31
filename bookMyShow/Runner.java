package com.avinash.lld.bookMyShow;

import com.avinash.lld.bookMyShow.managers.BookingManager;
import com.avinash.lld.bookMyShow.managers.EventRunnerManager;
import com.avinash.lld.bookMyShow.models.Seat;
import com.avinash.lld.bookMyShow.models.Show;
import com.avinash.lld.bookMyShow.models.ShowRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {

    public static  void  simpleRun(){
        // Initialize the Spring application context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.avinash.lld.bookMyShow");
        context.refresh();

        // Get beans from the context
        EventRunnerManager eventRunnerManager = context.getBean(EventRunnerManager.class);
        BookingManager bookingManager = context.getBean(BookingManager.class);

        // Create a show runner
        ShowRunner showRunner = new ShowRunner();
        showRunner.setName("Avinash Entertainment");
        ShowRunner createdShowRunner = eventRunnerManager.addShowRunner(showRunner);
        System.out.println("Created Show Runner: " + createdShowRunner);

        // Add a show to the show runner
        Show show = new Show();
        show.setName("Blockbuster Movie");
        show.setShowTime("7:00 PM");
        Show createdShow = eventRunnerManager.addShow(createdShowRunner.getId(), show);
        System.out.println("Created Show: " + createdShow);

        // Add seats to the show
        Seat seat1 = new Seat();
        Seat seat2 = new Seat();
        eventRunnerManager.addSeat(createdShowRunner.getId(), createdShow.getId(), seat1);
        eventRunnerManager.addSeat(createdShowRunner.getId(), createdShow.getId(), seat2);
        System.out.println("Seats added to the show.");

        // Try booking a seat
        Seat.SeatStatus status1 = bookingManager.createBooking(createdShow.getId(), seat1, seat1.getVersion());
        System.out.println("Seat 1 booking status: " + status1);

        // Try booking the same seat again (to test version mismatch handling)
        Seat.SeatStatus status2 = bookingManager.createBooking(createdShow.getId(), seat1, seat1.getVersion());
        System.out.println("Seat 1 booking status on second attempt: " + status2);

        // Try booking another seat
        Seat.SeatStatus status3 = bookingManager.createBooking(createdShow.getId(), seat2, seat2.getVersion());
        System.out.println("Seat 2 booking status: " + status3);

        // Cleanup the context
        context.close();
    }
    public static void concurrentRun(){
        // Initialize the Spring application context
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.avinash.lld.bookMyShow");
        context.refresh();

        // Get beans from the context
        EventRunnerManager eventRunnerManager = context.getBean(EventRunnerManager.class);
        BookingManager bookingManager = context.getBean(BookingManager.class);

        // Create a show runner
        ShowRunner showRunner = new ShowRunner();
        showRunner.setName("Avinash Entertainment");
        ShowRunner createdShowRunner = eventRunnerManager.addShowRunner(showRunner);

        // Add a show to the show runner
        Show show = new Show();
        show.setName("Blockbuster Movie");
        show.setShowTime("7:00 PM");
        Show createdShow = eventRunnerManager.addShow(createdShowRunner.getId(), show);

        // Add a seat to the show
        Seat seat = new Seat();
        eventRunnerManager.addSeat(createdShowRunner.getId(), createdShow.getId(), seat);

        // Simulate concurrent booking attempts using threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 3; i++) {
            final int userId = i; // User ID to identify the thread
            executorService.execute(() -> {
                System.out.println("User " + userId + " attempting to book the seat...");
                Seat.SeatStatus status = bookingManager.createBooking(createdShow.getId(), seat, seat.getVersion());
                System.out.println("User " + userId + " booking status: " + status);
            });
        }

        // Shut down the executor service
        executorService.shutdown();

        // Cleanup the context
        context.close();
    }
    public static void main(String[] args) {
      // simpleRun();
        //concurrentRun();
    }
}
