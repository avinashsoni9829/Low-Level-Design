package com.avinash.lld.rideShare.statePattern;

import com.avinash.lld.rideShare.Ride;

public class CompletedState implements RideState {
    @Override
    public void updateState(Ride ride) {
        System.out.println("Ride is Completed");
    }
}
