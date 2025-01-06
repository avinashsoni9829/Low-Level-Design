package com.avinash.lld.splitwise.strategy;


import com.avinash.lld.splitwise.models.User;

import java.util.HashMap;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy{
    @Override
    public Map<User, Double> split(double amount, Map<User, Double> details) {
        Map<User,Double> share = new HashMap<>();
        int cnt = details.size();
        double contri = amount/ cnt;
        for(User u : details.keySet())
        {
            share.put(u,contri);
        }
        return share;
    }
}
