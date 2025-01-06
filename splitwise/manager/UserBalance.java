package com.avinash.lld.splitwise.manager;


import com.avinash.lld.splitwise.models.User;

public class UserBalance {
    private User user;
    private double amount;

    public UserBalance(User user , double amt)
    {
         this.user = user;
         this.amount = amt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
