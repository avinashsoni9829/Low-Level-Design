package org.example.chainOfResponsiblity;

import java.util.ArrayList;
import java.util.Scanner;

public class PasswordBuilderCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter the password");
        String password = scanner.nextLine();

        PasswordBuilder builder = new PasswordBuilder(new ArrayList<>(),password);

        builder.lengthCheck().numericCharacterCheck().specialCharacterCheck().upperLetterCheck();

        builder.validatePassword();

    }
}
