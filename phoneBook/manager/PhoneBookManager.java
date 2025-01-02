package com.avinash.lld.phoneBook.manager;

import com.avinash.lld.phoneBook.exception.ContactAlreadyExistException;
import com.avinash.lld.phoneBook.exception.InvalidMobileNumberException;
import com.avinash.lld.phoneBook.exception.UserNotFoundException;
import com.avinash.lld.phoneBook.models.Contact;
import com.avinash.lld.phoneBook.models.User;
import com.avinash.lld.phoneBook.trie.Trie;
import com.avinash.lld.phoneBook.utils.Utils;

import java.util.*;


public class PhoneBookManager implements PhoneBookManagerActions {
    private Map<String, User> userRepository; // key = userId
    private Map<String,Contact> contactRepository; // key = contactId
    private Map<String, List<String>> userContactMap; // key = userId , val = List of contact Ids;

    private Map<String, Integer> spamMap; // key = spammer Mobile No

    private Map<String,List<String>> blockedContacts; // key = userId , val = List<MobileNo>

    private Map<String,List<Contact>> favouriteContacts;

    private Map<String,Trie> nameTries;
    private Map<String,Trie> phoneNumberTries;

    public PhoneBookManager(){
         userRepository = new HashMap<>();
         contactRepository = new HashMap<>();
         userContactMap = new HashMap<>();
         spamMap = new HashMap<>();
         blockedContacts = new HashMap<>();
         favouriteContacts = new HashMap<>();
         nameTries = new HashMap<>();
         phoneNumberTries = new HashMap<>();
    }

    @Override
    public User createUser(User user) {
        // validate Mobile No
        validateMobileNumber(user.getPhoneNumber());
        userRepository.put(user.getId(),user);
        return user;
    }



    @Override
    public Contact createContact(String userId, Contact c) {
        // Check if User Exist or Not
        checkIfUserIdExists(userId);
        validateMobileNumber(c.getPhoneNumber());
        // Check if Contact Already Exist
        if(!userContactMap.containsKey(userId)){
            userContactMap.put(userId,new ArrayList<>());
        }
        if(userContactMap.get(userId).contains(c.getId())){
            throw new ContactAlreadyExistException(c.getId() + " already Exist");
        }
        // Add the Contact with Details in Contact Repo
        contactRepository.put(c.getId(),c);
        // Map the Contact to the User id
        userContactMap.get(userId).add(c.getId());
        // add Contact Name to Name Trie
        if(nameTries.containsKey(userId))
        {
             nameTries.get(userId).insert(getFullName(c));

        }
        else{
             nameTries.put(userId,new Trie());
             nameTries.get(userId).insert(getFullName(c));
        }
        // add Phone Number to the PhoneNumber Trie
        if(phoneNumberTries.containsKey(userId))
        {
             phoneNumberTries.get(userId).insert(c.getPhoneNumber());
        }
        else {
             phoneNumberTries.put(userId,new Trie());
             phoneNumberTries.get(userId).insert(c.getPhoneNumber());
        }
        return c;

    }

    @Override
    public void addToBlockedContacts(String userId, String mobileNo) {
        // Check if User Exist or Not
        checkIfUserIdExists(userId);
        if(!blockedContacts.containsKey(userId)){
            blockedContacts.put(userId,new ArrayList<>());
        }
        else{
            blockedContacts.get(userId).add(mobileNo);
        }
  }

    @Override
    public void addToFavourites(String userId, Contact c) {
        // Check if User Exist or Not
        checkIfUserIdExists(userId);
        if(!favouriteContacts.containsKey(userId)){
            favouriteContacts.put(userId,new ArrayList<>());
        }
        else{
            favouriteContacts.get(userId).add(c);
        }
    }

    @Override
    public void markNoAsSpam(String number) {
       spamMap.compute(number, (key, val) -> (val == null) ? 1 : val + 1);
    }

    @Override
    public List<String> search(String userId,String input) {
        checkIfUserIdExists(userId);
        if(!nameTries.containsKey(userId) || !phoneNumberTries.containsKey(userId)){
             throw new UserNotFoundException(userId + "doesn't Exist");
        }
        return phoneBookSearch(nameTries.get(userId),phoneNumberTries.get(userId),input);
    }

    private List<String> phoneBookSearch(Trie nameTrie , Trie phoneNoTrie,String input)
    {
        List<String> nameResults = searchUtils(nameTrie,input);
        List<String> phoneResults = searchUtils(phoneNoTrie,input);
        nameResults.addAll(phoneResults);
        return nameResults;
    }
    private List<String> searchUtils(Trie trie,String input)
    {
         return trie.search(input);
    }

    private void checkIfUserIdExists(String userId)
    {
        if(!userRepository.containsKey(userId)){
            throw new UserNotFoundException(userId + "not Found");
        }
    }

    private String getFullName(Contact c)
    {
         return c.getFirstName().toLowerCase()+c.getLastName().toLowerCase();
    }

    private void validateMobileNumber(String mobile)
    {
       if(!Utils.isValidMobileNo(mobile)){
           throw new InvalidMobileNumberException("Invalid Mobile No ");
       }
    }
}
