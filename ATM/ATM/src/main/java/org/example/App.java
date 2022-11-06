package org.example;

import org.example.Machine.ATM;
import org.example.Machine.CurrencyComposition;
import org.example.Machine.Location;
import org.example.Machine.RunMachine;
import org.example.Machine.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        RunMachine machine = new RunMachine();
        Location location = new Location("INDIA","302018","RAJASTHAN","JAIPUR","GP","Street 10");
        CurrencyComposition composition = new CurrencyComposition(5000,5000,5000,5000,5000,5000,5000,5000);
        User user = new User("JOE","123",10000,false);
        ATM atm = machine.createATM(location,composition);

        // Test check balance
        machine.checkBalance(user);
        //  Test updateLocation
        Location newLocation = new Location("INDIA","302019","RAJASTHAN","JAIPUR","GPR","Street 70");
        machine.updateLocation(atm,newLocation);
        //  Test updateComposition
        CurrencyComposition newComposition = new CurrencyComposition(50000,5000,5000,5000,5000,5000,5000,5000);
        machine.updateComposition(atm,newComposition);

        // Test withdraw
        machine.withdraw(8890,user,atm);

        CurrencyComposition currencyComposition = new CurrencyComposition(0,0,0,5,5,0,0,0);
        User newUser = new User("ADAM","123",100000,false);
        ATM newATM = machine.createATM(location,currencyComposition);

        // Test when withdraw amount > user balance
        //machine.withdraw(2000000,newUser,newATM);


        // Test when machine balance < amount asked
        machine.withdraw(90000,newUser,newATM);

    }
}
