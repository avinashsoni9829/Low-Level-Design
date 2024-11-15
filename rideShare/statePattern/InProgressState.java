package com.avinash.lld.rideShare.statePattern;

import com.avinash.lld.rideShare.Ride;
import com.avinash.lld.rideShare.statePattern.RideState;

public class InProgressState implements RideState {
    @Override
    public void updateState(Ride ride) {
        System.out.println("Ride is in Progress");
    }
}
