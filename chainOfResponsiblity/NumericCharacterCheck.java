package org.example.chainOfResponsiblity;

public class NumericCharacterCheck implements Check{
    public  Check nextCheck;

    public NumericCharacterCheck()
    {


    }
    @Override
    public void validate(String password) {
         if(!password.matches(".*\\d.*"))
             System.out.println("password should contain at least 1 numeric character");
        if(nextCheck!=null)
        {
            nextCheck.validate(password);
        }

    }
}
