package com.avinash.lld.phoneBook.models;

import java.util.Map;

public class Global {
   private Map<String, Integer> spamMap;

   public void reportSpam(String no) {
      spamMap.compute(no, (key, val) -> (val == null) ? 1 : val + 1);
   }
}
