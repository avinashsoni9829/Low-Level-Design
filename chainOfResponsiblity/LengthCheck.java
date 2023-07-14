package org.example.chainOfResponsiblity;



public class LengthCheck implements Check{

    public Check nextCheck;

    public LengthCheck()
    {

    }

    @Override
    public void validate(String password) {
        if(password.length() < 6)
        {
            System.out.println("password length should be greater than 6");
        }

        if(nextCheck!=null)
        {
             nextCheck.validate(password);
        }

    }
}
