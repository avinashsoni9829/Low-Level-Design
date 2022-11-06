package org.example.Machine;


/**
 * Login type : ADMIN , USER
 * ADMIN FEATURES :
 * 1. Create an ATM
 * 2. Update the Composition
 * 3. Update the Location
 *
 * USER FEATURES :
 * 1. WITHDRAW
 *
 */

public class RunMachine implements MachineFeatures {
    @Override
    public ATM createATM(Location location, CurrencyComposition composition) {
        return new ATM(location,composition);
    }

    @Override
    public void updateLocation(ATM atm, Location newLocation) {
        atm.setLocation(newLocation);
    }

    @Override
    public void updateComposition(ATM atm, CurrencyComposition composition) {
        atm.setComposition(composition);
    }

    @Override
    public void seeComposition(CurrencyComposition composition) {
        System.out.println("THE ATM COMPOSITION IS : ");
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("1. 2000 = " + composition.getTwo_Thousand());
        System.out.println("2. 1000 = " + composition.getThousand());
        System.out.println("3. 500 = " + composition.getFive_Hundred());
        System.out.println("4. 200 = " + composition.getTwo_Hundred());
        System.out.println("5. 100 = " + composition.getHundred());
        System.out.println("6. 50 = " + composition.getFifty());
        System.out.println("7. 20 = " + composition.getTwenty());
        System.out.println("8. 10 = " + composition.getTen());

        System.out.println("--------------------------------------------------------------------------------");

    }

    /**
     * Transaction Should be a Multiple of 10
     * @param amount
     * @param user
     * @param atm
     * @throws Exception
     */
    @Override
    public void withdraw(int amount, User user, ATM atm) throws Exception {
        if(amount > user.getBalance()){
            atm.getTransactionsList().add(new Transactions(TransactionType.FAILED,amount,Verdict.FAILURE,atm.getLocation()));
            throw new Exception("Your Balance is Not Sufficient . Please Add Funds to Your Account");
         }

        if(amount > atm.getBalance()){
            atm.getTransactionsList().add(new Transactions(TransactionType.FAILED,amount,Verdict.OUT_OF_MONEY,atm.getLocation()));
             throw new Exception("We are sorry! we are out of funds");
        }
        // Do the Transaction
        atm.getTransactionsList().add(new Transactions(TransactionType.DEBIT,amount,Verdict.SUCCESS,atm.getLocation()));
        updateNoteComposition(atm,amount);
        // Deduct Amount from User Account
        System.out.println(user.getUsername() + "! Your Account is DEBITED with " + amount);
        user.setBalance(user.getBalance() - amount);
        checkBalance(user);

    }

    @Override
    public void checkBalance(User user) {
        System.out.println("Your balance is : " + user.getBalance());
    }

    private void updateNoteComposition(ATM atm, int amount) {

        CurrencyComposition composition = atm.getComposition();
        int two_thousand_use =  (amount/2000);

        if(composition.getTwo_Thousand() >= two_thousand_use){
            amount = amount - two_thousand_use * 2000;
            composition.setTwo_Thousand(composition.getTwo_Thousand() - two_thousand_use);
        }

        int thousand_use =  (amount/1000);
        if(composition.getThousand() >= thousand_use){
            amount = amount - thousand_use * 1000;
            composition.setThousand(composition.getThousand() - thousand_use);
        }

        int five_hundred_use =  (amount/500);
        if(composition.getFive_Hundred() >= five_hundred_use){
            amount = amount - five_hundred_use * 500;
            composition.setFive_Hundred(composition.getFive_Hundred()-five_hundred_use);
        }

        int two_hundred_use =  (amount/200);
        if(composition.getTwo_Hundred() >= two_hundred_use){
            amount = amount - two_hundred_use * 200;
            composition.setTwo_Hundred(composition.getTwo_Hundred() - two_hundred_use);
        }

        int hundred_use =  (amount/100);
        if(composition.getHundred() >= hundred_use){
            amount = amount - hundred_use * 100;
            composition.setHundred(composition.getHundred() - hundred_use);
        }

        int fifty_use = (amount/50);
        if(composition.getFifty() >= fifty_use){
            amount = amount - fifty_use * 50;
            composition.setFifty(composition.getFifty() - fifty_use);
        }

        int twenty_use = (amount/20);
        if(composition.getTwenty() >= twenty_use){
            amount = amount - twenty_use * 20;
            composition.setTwenty(composition.getTwenty() - twenty_use);
        }

        int ten_use =  (amount/10);
        if(composition.getTen() >= ten_use){
            amount = amount - ten_use * 10;
            composition.setTen(composition.getTen() - ten_use);
        }

        updateComposition(atm,composition);
        seeComposition(atm.getComposition());
    }

}
