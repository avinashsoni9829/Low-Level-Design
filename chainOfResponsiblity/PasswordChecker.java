package org.example.chainOfResponsiblity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PasswordChecker {
    public Check nextCheck;


    public PasswordChecker(){

    }

    public void validatePassword(String password)
    {

         if(nextCheck!=null)
         {
            nextCheck.validate(password);

         }
    }

}
