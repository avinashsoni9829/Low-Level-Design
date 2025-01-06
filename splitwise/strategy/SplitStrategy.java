package com.avinash.lld.splitwise.strategy;



import com.avinash.lld.splitwise.models.User;

import java.util.Map;

public interface SplitStrategy {
    Map<User,Double> split(double amount,Map<User,Double> details);
}
