package com.avinash.lld.rideShare;


public class Location {
  private int x;
  private int y;

  public Location(int x , int y)
  {
     this.x = x ;
     this.y = y ;

  }

  public double getDistance(Location l)
  {
     return Math.sqrt(Math.pow(this.x - l.x,2) + Math.pow(this.y - l.y , 2));
  }

}
