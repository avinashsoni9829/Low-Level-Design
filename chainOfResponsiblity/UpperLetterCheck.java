package org.example.chainOfResponsiblity;

public class UpperLetterCheck implements Check{
    public Check nextCheck;

    public UpperLetterCheck()
    {

    }
    @Override
    public void validate(String password) {
        if(!password.matches(".*[A-Z].*"))
            System.out.println("password should have at least one capital Alphabet");
        if(nextCheck!=null)
        {
            nextCheck.validate(password);
        }

    }
}
