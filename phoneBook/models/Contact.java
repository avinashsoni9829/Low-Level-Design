package com.avinash.lld.phoneBook.models;


import static com.avinash.lld.phoneBook.utils.Utils.generateId;

public class Contact {
    private String id;
    private String firstName;
    private String lastName;

    private String phoneNumber;

    public Contact(String firstName , String lastName , String phoneNumber)
    {
         this.id = generateId();
         this.firstName = firstName;
         this.lastName = lastName;
         this.phoneNumber = phoneNumber;
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
}
