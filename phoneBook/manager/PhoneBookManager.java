package com.avinash.lld.phoneBook.manager;

import com.avinash.lld.phoneBook.exception.ContactAlreadyExistException;
import com.avinash.lld.phoneBook.exception.InvalidMobileNumberException;
import com.avinash.lld.phoneBook.exception.UserNotFoundException;
import com.avinash.lld.phoneBook.models.Contact;
import com.avinash.lld.phoneBook.models.User;
import com.avinash.lld.phoneBook.trie.Trie;
import com.avinash.lld.phoneBook.utils.Utils;

import java.util.*;

/**
 * PhoneBookManager is responsible for managing users and their associated contacts, blocked numbers,
 * favorite contacts, and spam management.
 */
public class PhoneBookManager implements PhoneBookManagerActions {

    private Map<String, User> userRepository; // Stores user data with userId as the key
    private Map<String, Contact> contactRepository; // Stores contact data with contactId as the key
    private Map<String, List<String>> userContactMap; // Maps userId to a list of contactIds

    private Map<String, Integer> spamMap; // Tracks spam counts by mobile number
    private Map<String, List<String>> blockedContacts; // Maps userId to a list of blocked mobile numbers
    private Map<String, List<Contact>> favouriteContacts; // Maps userId to a list of favorite contacts

    private Map<String, Trie> nameTries; // Maps userId to a Trie for contact names
    private Map<String, Trie> phoneNumberTries; // Maps userId to a Trie for contact phone numbers

    /**
     * Constructor to initialize all required data structures.
     */
    public PhoneBookManager() {
        userRepository = new HashMap<>();
        contactRepository = new HashMap<>();
        userContactMap = new HashMap<>();
        spamMap = new HashMap<>();
        blockedContacts = new HashMap<>();
        favouriteContacts = new HashMap<>();
        nameTries = new HashMap<>();
        phoneNumberTries = new HashMap<>();
    }

    /**
     * Creates a new user.
     * @param user The user to be added.
     * @return The added user.
     */
    @Override
    public User createUser(User user) {
        validateMobileNumber(user.getPhoneNumber());
        userRepository.put(user.getId(), user);
        return user;
    }

    /**
     * Creates a new contact for a given user.
     * @param userId The ID of the user.
     * @param c The contact to be added.
     * @return The added contact.
     */
    @Override
    public Contact createContact(String userId, Contact c) {
        checkIfUserIdExists(userId);
        validateMobileNumber(c.getPhoneNumber());

        // Initialize user's contact list if not present
        userContactMap.computeIfAbsent(userId, k -> new ArrayList<>());

        // Check if the contact already exists for the user
        if (userContactMap.get(userId).contains(c.getId())) {
            throw new ContactAlreadyExistException(c.getId() + " already exists");
        }

        // Add the contact to the contact repository
        contactRepository.put(c.getId(), c);

        // Map the contact to the user's contact list
        userContactMap.get(userId).add(c.getId());

        // Add the contact's name to the user's name Trie
        nameTries.computeIfAbsent(userId, k -> new Trie())
                .insert(getFullName(c));

        // Add the contact's phone number to the user's phone number Trie
        phoneNumberTries.computeIfAbsent(userId, k -> new Trie())
                .insert(c.getPhoneNumber());

        return c;
    }

    /**
     * Adds a mobile number to the user's blocked contacts.
     * @param userId The ID of the user.
     * @param mobileNo The mobile number to be blocked.
     */
    @Override
    public void addToBlockedContacts(String userId, String mobileNo) {
        checkIfUserIdExists(userId);
        blockedContacts.computeIfAbsent(userId, k -> new ArrayList<>())
                .add(mobileNo);
    }

    /**
     * Adds a contact to the user's favorites list.
     * @param userId The ID of the user.
     * @param c The contact to be added to favorites.
     */
    @Override
    public void addToFavourites(String userId, Contact c) {
        checkIfUserIdExists(userId);
        favouriteContacts.computeIfAbsent(userId, k -> new ArrayList<>())
                .add(c);
    }

    /**
     * Marks a phone number as spam, incrementing its spam count.
     * @param number The phone number to be marked as spam.
     */
    @Override
    public void markNoAsSpam(String number) {
        spamMap.compute(number, (key, val) -> (val == null) ? 1 : val + 1);
    }

    /**
     * Searches for a contact or phone number for a user.
     * @param userId The ID of the user.
     * @param input The search input (name or phone number).
     * @return A list of matching names and phone numbers.
     */
    @Override
    public List<String> search(String userId, String input) {
        checkIfUserIdExists(userId);

        if (!nameTries.containsKey(userId) || !phoneNumberTries.containsKey(userId)) {
            throw new UserNotFoundException(userId + " doesn't exist");
        }

        return phoneBookSearch(nameTries.get(userId), phoneNumberTries.get(userId), input);
    }

    /**
     * Searches for matching names and phone numbers in Tries.
     * @param nameTrie The Trie for names.
     * @param phoneNoTrie The Trie for phone numbers.
     * @param input The search input.
     * @return A list of matching names and phone numbers.
     */
    private List<String> phoneBookSearch(Trie nameTrie, Trie phoneNoTrie, String input) {
        List<String> nameResults = searchUtils(nameTrie, input);
        List<String> phoneResults = searchUtils(phoneNoTrie, input);
        nameResults.addAll(phoneResults);
        return nameResults;
    }

    /**
     * Helper method to search in a Trie.
     * @param trie The Trie to search.
     * @param input The search input.
     * @return A list of matches.
     */
    private List<String> searchUtils(Trie trie, String input) {
        return trie.search(input);
    }

    /**
     * Validates whether a user exists in the system.
     * @param userId The ID of the user.
     */
    private void checkIfUserIdExists(String userId) {
        if (!userRepository.containsKey(userId)) {
            throw new UserNotFoundException(userId + " not found");
        }
    }

    /**
     * Retrieves the full name of a contact in lowercase format.
     * @param c The contact.
     * @return The full name in lowercase.
     */
    private String getFullName(Contact c) {
        return c.getFirstName().toLowerCase() + c.getLastName().toLowerCase();
    }

    /**
     * Validates whether a mobile number is valid.
     * @param mobile The mobile number to validate.
     */
    private void validateMobileNumber(String mobile) {
        if (!Utils.isValidMobileNo(mobile)) {
            throw new InvalidMobileNumberException("Invalid mobile number");
        }
    }
}
