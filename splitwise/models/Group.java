package com.avinash.lld.splitwise.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {
    private String id;
    private String name;
    private List<User> members;
    private List<Expense> transactionHistory;
    private Map<User,Double> balanceSheet;

    public Group(String id, String name, List<User> members) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.transactionHistory= new ArrayList<>();
        this.balanceSheet = new HashMap<>();
    }



    public void addExpense(Expense expense)
    {
        Map<User,Double> shares = expense.calculateShares();
        User payer = expense.getPayer();
        transactionHistory.add(expense);

        for(User u : shares.keySet())
        {
             double share = shares.get(u);

             if(!u.equals(payer))
             {
                 balanceSheet.put(u,balanceSheet.getOrDefault(u,0.0) - share);
                 balanceSheet.put(payer,balanceSheet.getOrDefault(payer,0.0) + share);

                 u.updateBalance(payer,-share);
                 payer.updateBalance(u,share);
             }
        }
    }

    public Map<User, Double> getBalanceSheet() {
        return balanceSheet;
    }
}
