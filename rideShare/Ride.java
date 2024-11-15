package com.avinash.lld.rideShare;

import com.avinash.lld.rideShare.statePattern.RequestState;
import com.avinash.lld.rideShare.statePattern.RideState;

public class Ride {
    private String rideId;
    private Rider rider;
    private VehicleDriver driver;

    private Location pickUp;
    private Location drop;

    private RideState state;

    public Ride(Builder builder)
    {
         this.rideId = "R" + System.currentTimeMillis();
         this.rider = builder.rider;
         this.driver = builder.driver;
         this.pickUp = builder.pickUp;
         this.drop = builder.drop;
         this.state = new RequestState();
    }

    public String getRideId(){
        return rideId;
    }

    public Location getPickUpLocation(){
         return pickUp;
    }
    public Location getDropLocation()
    {
         return drop;
    }

    public void  setDriver(VehicleDriver driver){
         this.driver = driver;
    }

    public void  setRider(Rider rider)
    {
         this.rider = rider;
    }

    public void  setState(RideState rideState)
    {
         this.state = rideState;
    }

    public static class Builder{
        private Rider rider;
        private VehicleDriver driver;

        private Location pickUp;
        private Location drop;



        public Builder withRider(Rider rider){
            this.rider = rider;
            return this;
        }

        public Builder withDriver(VehicleDriver driver){
            this.driver = driver;
            return this;
        }

        public Builder withPickup(Location pick)
        {
            this.pickUp = pick;
            return this;
        }

        public Builder withDrop(Location drop)
        {

            this.drop  = drop;
            return this;

        }

        public Ride build()
        {
             return new Ride(this);
        }


    }
}
