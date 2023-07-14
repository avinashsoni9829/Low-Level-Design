package org.example.chainOfResponsiblity;

public class SpecialCharacterCheck implements Check{
    public Check nextCheck;

    public SpecialCharacterCheck()
    {
    }
    @Override
    public void validate(String password) {
        if(!password.matches(".*[@!#*].*"))
            System.out.println("password should contain at least one special character @ ! # * ");
        if(nextCheck!=null)
        {
            nextCheck.validate(password);
        }

    }

}
