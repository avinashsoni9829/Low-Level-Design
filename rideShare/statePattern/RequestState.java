package com.avinash.lld.rideShare.statePattern;

import com.avinash.lld.rideShare.Ride;

public class RequestState implements RideState {
    @Override
    public void updateState(Ride ride) {
        System.out.println("Rode is In Request Stage");
    }
}
