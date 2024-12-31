package com.avinash.lld.bookMyShow.models;

public class Event {
    private String id;
    private String name;

    private long durationInSeconds;


    public Event(String id , String name , long duration , EventType eventType)
    {
         this.id = id;
         this.name = name;
         this.durationInSeconds = duration;
         this.eventType = eventType;
    }

    private EventType eventType;


    public  enum EventType {
         MOVIE,
         CONCERT,
         COMEDY_SHOW
    }
}
