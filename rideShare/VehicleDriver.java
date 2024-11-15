package com.avinash.lld.rideShare;

import com.avinash.lld.rideShare.observer.Observer;

public class VehicleDriver extends User implements DriverActions, Observer {
    private Location location;
    private boolean isAvailable;

    public VehicleDriver(String userId,String userName)
    {
         super(userId,userName);
         this.isAvailable  = true;
         this.location = new Location(0,0);
    }
    @Override
    public void acceptRide(Ride ride) {
        System.out.println(userName + " has accepted " + ride.getRideId());
    }

    @Override
    public void notify(String message) {
        System.out.println(" Notification " + message);
    }

    public Location getLocation(){
        return location;
    }
}
