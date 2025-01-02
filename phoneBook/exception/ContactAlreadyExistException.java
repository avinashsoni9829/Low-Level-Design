package com.avinash.lld.phoneBook.exception;

public class ContactAlreadyExistException extends RuntimeException{
    public ContactAlreadyExistException(String message)
    {
         super(message);
    }
}
