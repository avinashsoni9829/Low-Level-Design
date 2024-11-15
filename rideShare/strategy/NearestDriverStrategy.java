package com.avinash.lld.rideShare.strategy;

import com.avinash.lld.rideShare.Location;
import com.avinash.lld.rideShare.VehicleDriver;

import java.util.List;

public class NearestDriverStrategy{
   public VehicleDriver findDriver(Location pickup, List<VehicleDriver>drivers)
   {
        VehicleDriver driver = null;
        double minDist = Double.MAX_VALUE;
        for(VehicleDriver d : drivers)
        {
             double dis = d.getLocation().getDistance(pickup);
             if(dis < minDist){
                  minDist = dis;
                  driver = d;
             }
        }

        return driver;
   }
}
