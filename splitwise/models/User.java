package com.avinash.lld.splitwise.models;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String id;
    private String name;
    Map<User,Double> balanceSheet;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.balanceSheet = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<User, Double> getBalanceSheet() {
        return balanceSheet;
    }

    public void setBalanceSheet(Map<User, Double> balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    public void updateBalance(User user, double amount)
    {
         balanceSheet.put(user,balanceSheet.getOrDefault(user,0.0) + amount);
    }
}
