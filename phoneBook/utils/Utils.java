package com.avinash.lld.phoneBook.utils;

import java.util.UUID;

public class Utils {
    private Utils()
    {

    }

    public static String generateId(){
        return UUID.randomUUID().toString();
    }

    public static boolean isValidMobileNo(String input) {
        String regex = "^[6-9]\\d{9}$";
        return input != null && input.matches(regex);
    }
}
