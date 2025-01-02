package com.avinash.lld.phoneBook.trie;

import java.util.List;

public class TrieRunner {
    public static void main(String[] args) {
        List<String> names = List.of(
                "Avinash Soni",
                "Avishek Soni",
                "Avidharj",
                "Avisont",
                "Aoiuravi",
                "Babil",
                "Bashir",
                "Bong",
                "Bona"
        );
        Trie trie = new Trie();
        for (String name : names) {
            trie.insert(name);
        }

        String searchPrefix = "bafes";
        List<String> results = trie.search(searchPrefix);

        // Display results
        System.out.println("Names starting with \"" + searchPrefix + "\":");
        for (String name : results) {
            System.out.println(name);
        }
    }
}
