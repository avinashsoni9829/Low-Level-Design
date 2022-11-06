package org.example.Machine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
public class ATM {

    private String atmId;
    private Location location;
    private CurrencyComposition composition;
    private int balance;
    private Condition condition;
    private List<Transactions> transactionsList = new ArrayList<>();


    public ATM(Location location , CurrencyComposition composition){
      this.location = location;
      this.composition = composition;
      balance = composition.getTwo_Thousand() * 2000 + composition.getThousand() * 1000 + composition.getFive_Hundred() * 500
                + composition.getTwo_Hundred() * 200 + composition.getHundred() * 100 + composition.getFifty() * 50
                + composition.getTwenty() * 20 + composition.getTen() * 10;

      condition = Condition.WORKING;
    }



}
