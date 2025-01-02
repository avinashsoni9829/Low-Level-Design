package com.avinash.lld.phoneBook.models;

import com.avinash.lld.phoneBook.utils.Utils;

import java.util.List;

import static com.avinash.lld.phoneBook.utils.Utils.generateId;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    List<Contact> contacts;
    List<Contact> blockedContacts;

    List<Contact> favourites;



    private AccountType type = AccountType.PERSONAL;

    public enum AccountType{
        BUSINESS,
        PERSONAL

    }

    public User(String firstName , String lastName , String mobileNo , AccountType type )
    {
         id = generateId();
         this.firstName = firstName;
         this.lastName = lastName;
         this.phoneNumber = mobileNo;
         this.type = type;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getBlockedContacts() {
        return blockedContacts;
    }

    public void setBlockedContacts(List<Contact> blockedContacts) {
        this.blockedContacts = blockedContacts;
    }

    public List<Contact> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<Contact> favourites) {
        this.favourites = favourites;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }



}
