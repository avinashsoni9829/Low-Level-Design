package com.avinash.lld.rideShare;



/**
 * User Class is Made Abstract So Other Classes Can Use this Like Rider , Driver etc.
 * as id and name will be a Common Property
 */

public abstract class User {
    protected String userId;
    protected String userName;

    public User(String userId,String userName)
    {
         this.userId = userId;
         this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }
}
