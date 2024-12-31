package com.avinash.lld.bookMyShow.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Movie extends Event{
    private String genre;
    private Date releaseDate;

    public Movie(String id, String name, long duration, EventType eventType,String genre,Date releaseDate) {
        super(id, name, duration, eventType);
        this.genre = genre;
        if(Objects.nonNull(releaseDate)){
             this.releaseDate = releaseDate;
        }
        else{
             this.releaseDate = new Date();
        }
    }
}
