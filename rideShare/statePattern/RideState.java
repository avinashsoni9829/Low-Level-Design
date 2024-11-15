package com.avinash.lld.rideShare.statePattern;

import com.avinash.lld.rideShare.Ride;

public interface RideState {
    void updateState(Ride ride);
}
