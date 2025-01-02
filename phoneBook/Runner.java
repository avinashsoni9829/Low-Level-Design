package com.avinash.lld.phoneBook;

import com.avinash.lld.phoneBook.manager.PhoneBookManager;
import com.avinash.lld.phoneBook.models.Contact;
import com.avinash.lld.phoneBook.models.User;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        PhoneBookManager manager = new PhoneBookManager();

        User user = new User("Avinash","Soni","9314632311", User.AccountType.PERSONAL);
        User t =  manager.createUser(user);
        System.out.println(t.getId());

        Contact c1 = new Contact("Abhishek", "Soni","8302999879");
        Contact c2 = new Contact("Ankur", "Soni","9829761019");
        Contact c3 = new Contact("Madan", "Soni","9414048879");
        Contact c4 = new Contact("Mamta", "Soni","9414048873");
        Contact c5 = new Contact("Abhijeet", "Soni","8302999873");

        manager.createContact(user.getId(),c1);
        manager.createContact(user.getId(),c2);
        manager.createContact(user.getId(),c3);
        manager.createContact(user.getId(),c4);
        manager.createContact(user.getId(),c5);

        manager.addToBlockedContacts(user.getId(),"1234567891");
        manager.addToBlockedContacts(user.getId(),"1234567892");

        manager.markNoAsSpam("1234567893");
        manager.markNoAsSpam("1234567894");
        List<String> searchRes1 = manager.search(user.getId(),"abhi");
        System.out.println(searchRes1);

        List<String> searchRes2 = manager.search(user.getId(),"ma");
        System.out.println(searchRes2);

        List<String> searchRes3 = manager.search(user.getId(),"av");
        System.out.println(searchRes3);
    }
}
