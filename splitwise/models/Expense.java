package com.avinash.lld.splitwise.models;


import com.avinash.lld.splitwise.strategy.SplitStrategy;

import java.util.Map;

public class Expense {
    private User payer;
    private double amount;
    private SplitStrategy splitStrategy;
    private Map<User,Double> splitDetails;

    public Expense(User payer, double amount, SplitStrategy splitStrategy, Map<User, Double> splitDetails) {
        this.payer = payer;
        this.amount = amount;
        this.splitStrategy = splitStrategy;
        this.splitDetails = splitDetails;
    }


    public Map<User,Double> calculateShares()
    {
         return splitStrategy.split(amount,splitDetails);
    }

    public User getPayer(){
         return payer;
    }



}
