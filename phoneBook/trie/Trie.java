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
           crawl.children.putIfAbsent(c,new TrieNode());
           crawl = crawl.children.get(c);
         }
         crawl.isEndOfWord = true;
    }

    public List<String> search(String word)
    {
         TrieNode crawl = root;
         List<String> results  = new ArrayList<>();

         for(char c : word.toLowerCase().toCharArray())
         {
              if(!crawl.children.containsKey(c)) return results;
              crawl = crawl.children.get(c);
         }
         collectWords(crawl,new StringBuilder(word),results);

         return results;

    }

    private void collectWords(TrieNode crawl, StringBuilder stringBuilder, List<String> results) {
        if(crawl.isEndOfWord){
             results.add(stringBuilder.toString());
        }

        for(Map.Entry<Character,TrieNode> e : crawl.children.entrySet())
        {
             collectWords(e.getValue(),stringBuilder.append(e.getKey()),results);
             stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}
