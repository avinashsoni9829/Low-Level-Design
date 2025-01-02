package com.avinash.lld.phoneBook.exception;

public class InvalidMobileNumberException extends RuntimeException{
    public InvalidMobileNumberException(String message)
    {
         super(message);
    }
}
