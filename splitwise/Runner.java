package com.avinash.lld.splitwise;

import com.avinash.lld.splitwise.manager.SplitWiseManager;
import com.avinash.lld.splitwise.models.Expense;
import com.avinash.lld.splitwise.models.Group;
import com.avinash.lld.splitwise.models.User;
import com.avinash.lld.splitwise.strategy.EqualSplitStrategy;

import java.util.Arrays;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        User A = new User("1", "A");
        User B = new User("2", "B");
        User C = new User("3", "C");
        User D = new User("4", "D");

        Group gA = new Group("g1", "Group A", Arrays.asList(A, B, C, D));
        Group gB = new Group("g2", "Group B", Arrays.asList(A, B, C));

        gA.addExpense(new Expense(A, 3000, new EqualSplitStrategy(), Map.of(A, 1.0, B, 1.0, C, 1.0, D, 1.0)));
        gA.addExpense(new Expense(B, 2000, new EqualSplitStrategy(), Map.of(C, 1.0, D, 1.0)));
        gA.addExpense(new Expense(C, 5000, new EqualSplitStrategy(), Map.of(A, 1.0, B, 1.0, C, 1.0, D, 1.0)));
        gA.addExpense(new Expense(D, 200, new EqualSplitStrategy(), Map.of(A, 1.0)));
        gA.addExpense(new Expense(A, 500, new EqualSplitStrategy(), Map.of(A, 1.0, B, 1.0, C, 1.0, D, 1.0)));


        gB.addExpense(new Expense(A, 3000,new EqualSplitStrategy(), Map.of(A, 1.0, B, 1.0, C, 1.0)));
        gB.addExpense(new Expense(B, 2000,new EqualSplitStrategy(), Map.of(A, 1.0, B, 1.0, C, 1.0)));
        gB.addExpense(new Expense(C, 1000,new EqualSplitStrategy(), Map.of(A, 1.0, B, 1.0, C, 1.0)));

        A.updateBalance(B, -300);
        B.updateBalance(A, 300);

        System.out.println("Balances for Group A:");
        System.out.println(gA.getBalanceSheet());

        System.out.println("Balances for Group B:");
        System.out.println(gB.getBalanceSheet());

        SplitWiseManager settlementService = SplitWiseManager.getInstance();
        System.out.println("Settlements for Group A:");
        settlementService.minimizeTransactions(gA.getBalanceSheet()).forEach(System.out::println);

        System.out.println("Settlements for Group B:");
        settlementService.minimizeTransactions(gB.getBalanceSheet()).forEach(System.out::println);

    }
}
