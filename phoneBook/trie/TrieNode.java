package com.avinash.lld.phoneBook.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character,TrieNode> children;
    boolean isEndOfWord;

    public TrieNode()
    {
         children = new HashMap<>();
         isEndOfWord = false;
    }
}
