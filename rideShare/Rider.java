package com.avinash.lld.rideShare;


public class Rider extends User implements RiderActions {

    /**
     * @param userId UserId
     * @param name Name
     * Call the Parent Abstract Class Constructor to create the Object of the Rider
     */
    public Rider(String userId,String name){
         super(userId,name);
    }

    @Override
    public void rateDriver(VehicleDriver driver, int rating) {
        System.out.println(driver.getUserName() + " rated with  " + rating + " by " + userName);
    }
}
