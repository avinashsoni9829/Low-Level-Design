package com.avinash.lld.splitwise.manager;

import com.avinash.lld.splitwise.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplitWiseManager implements SplitManagerActions{
    private static SplitWiseManager instance = new SplitWiseManager();
    private SplitWiseManager()
    {

    }

    public static SplitWiseManager getInstance(){
         return instance;
    }

    @Override
    public List<String> minimizeTransactions(Map<User, Double> balanceSheet) {
        List<UserBalance> creditors = new ArrayList<>();
        List<UserBalance> debitors = new ArrayList<>();

        for(Map.Entry<User,Double> e  : balanceSheet.entrySet())
        {
            if(e.getValue() > 0) creditors.add(new UserBalance(e.getKey(),e.getValue()));
            else debitors.add(new UserBalance(e.getKey(),-1 * e.getValue()));
        }
        List<String> txn = new ArrayList<>();
        int i = 0 , j = 0;
        while(i < creditors.size() && j < debitors.size())
        {
             UserBalance creditor = creditors.get(i);
             UserBalance debitor = debitors.get(i);

             double min = Math.min(creditor.getAmount(),debitor.getAmount());
             txn.add(debitor.getUser().getName() + "Pays " + min + " to " + creditor.getUser().getName());
             creditor.setAmount(creditor.getAmount() - min);
             debitor.setAmount(debitor.getAmount() - min);

             if(creditor.getAmount() == 0) i++;
             if(debitor.getAmount() == 0) j++;
        }
        return txn;
    }
}
