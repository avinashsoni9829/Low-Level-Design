package com.avinash.lld.rideShare;

import com.avinash.lld.rideShare.factory.RideFactory;

public class Runner {
    public static void main(String[] args) {
        RideManager rideManager = RideManager.getInstance();

        Rider rider = (Rider) RideFactory.createUser("RIDER","r1","Avinash");
        VehicleDriver driver = (VehicleDriver) RideFactory.createUser("DRIVER","d1","driver1");
        VehicleDriver driver1 = (VehicleDriver) RideFactory.createUser("DRIVER","d2","driver2");

        rideManager.addDriver(driver);
        rideManager.addDriver(driver1);

        Ride ride = new Ride.Builder().withRider(rider).withPickup(new Location(10,10)).withDrop(new Location(20,20)).build();

        rideManager.bookRide(ride);

        driver1.acceptRide(ride);

        rideManager.completeRide(ride.getRideId());

        rider.rateDriver(driver1,3);
    }
}
