package com.avinash.lld.phoneBook.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void insert(String word)
    {
         TrieNode crawl = root;
         for(char c : word.toLowerCase().toCharArray())
         {
             crawl = crawl.children.computeIfAbsent(c, k -> new TrieNode());
         }
         crawl.isEndOfWord = true;
    }

    public List<String> search(String word)
    {
        List<String> results = new ArrayList<>();
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return results;
            }
        }
        collectWords(current, new StringBuilder(word), results);
        return results;

    }

    private void collectWords(TrieNode node, StringBuilder prefix, List<String> results) {
        if (node.isEndOfWord) {
            results.add(prefix.toString());
        }
        node.children.forEach((key, child) -> {
            prefix.append(key);
            collectWords(child, prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        });
    }
}
