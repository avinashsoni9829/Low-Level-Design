package com.avinash.lld.phoneBook;

import com.avinash.lld.phoneBook.manager.PhoneBookManager;
import com.avinash.lld.phoneBook.models.Contact;
import com.avinash.lld.phoneBook.models.User;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        PhoneBookManager manager = new PhoneBookManager();

        User user = new User("Avinash","Soni","9314632311", User.AccountType.PERSONAL);
        manager.createUser(user);



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

        User user2 = new User("Avinashs","Soni","9314632312", User.AccountType.BUSINESS);
        manager.createUser(user2);

        Contact c11 = new Contact("Abhishesk", "Ssoni","8302999879");
        Contact c22 = new Contact("Anskur", "Soni","9829761019");
        Contact c33 = new Contact("Madsan", "Soni","9414048879");
        Contact c44 = new Contact("Mamsta", "Soni","9414048873");
        Contact c55 = new Contact("Abhsijeet", "Soni","8302999873");
        manager.createContact(user2.getId(),c11);
        manager.createContact(user2.getId(),c22);
        manager.createContact(user2.getId(),c33);
        manager.createContact(user2.getId(),c44);
        manager.createContact(user2.getId(),c55);


        List<String> searchRes1 = manager.search(user.getId(),"abhi");
        System.out.println(searchRes1);

        List<String> searchRes2 = manager.search(user.getId(),"ma");
        System.out.println(searchRes2);

        List<String> searchRes3 = manager.search(user.getId(),"av");
        System.out.println(searchRes3);

        List<String> searchRes11 = manager.search(user2.getId(),"abhi");
        System.out.println(searchRes11);

        List<String> searchRes22 = manager.search(user2.getId(),"94");
        System.out.println(searchRes22);

        List<String> searchRes33 = manager.search(user2.getId(),"av");
        System.out.println(searchRes33);
    }
}
