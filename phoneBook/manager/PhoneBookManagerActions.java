package com.avinash.lld.phoneBook.manager;

import com.avinash.lld.phoneBook.models.Contact;
import com.avinash.lld.phoneBook.models.User;

import java.util.List;

public interface PhoneBookManagerActions {
    User createUser(User user);
    Contact createContact(String userId,Contact c);

    void addToBlockedContacts(String userId,String mobileNo);
    void addToFavourites(String userId,Contact c);

    void  markNoAsSpam(String number);

    List<String> search(String userId,String input);
}
