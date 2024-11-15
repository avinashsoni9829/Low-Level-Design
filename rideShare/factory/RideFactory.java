package com.avinash.lld.rideShare.factory;


import com.avinash.lld.rideShare.Rider;
import com.avinash.lld.rideShare.User;
import com.avinash.lld.rideShare.VehicleDriver;

public class RideFactory {
    public static User createUser(String type , String userId, String name)
    {
        return switch (type) {
            case "RIDER" -> new Rider(userId, name);
            case "DRIVER" -> new VehicleDriver(userId, name);
            default -> throw new IllegalArgumentException("No such User Found");
        };

    }
}
