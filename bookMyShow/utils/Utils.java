package com.avinash.lld.bookMyShow.utils;

import java.util.UUID;

public class Utils {
    private Utils(){

    }

    public static String getRandomID(){
         return String.valueOf(UUID.randomUUID());
    }
}
