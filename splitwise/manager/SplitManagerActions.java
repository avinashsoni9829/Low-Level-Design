package com.avinash.lld.splitwise.manager;

import com.avinash.lld.splitwise.models.User;

import java.util.List;
import java.util.Map;

public interface SplitManagerActions {
    List<String> minimizeTransactions(Map<User,Double> balanceSheet);
}
