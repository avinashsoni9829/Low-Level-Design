package org.example.Machine;

public interface MachineFeatures {
    public ATM createATM(Location location , CurrencyComposition composition);
    public void updateLocation(ATM atm , Location newLocation);
    public void updateComposition(ATM atm , CurrencyComposition composition);


    public void seeComposition(CurrencyComposition composition);

    public void withdraw(int amount , User user , ATM atm) throws Exception;
    public void checkBalance(User user);

}
