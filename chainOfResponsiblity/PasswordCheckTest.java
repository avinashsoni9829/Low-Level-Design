package org.example.chainOfResponsiblity;

import java.util.Scanner;

public class PasswordCheckTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter the password");
        String password = scanner.nextLine();

        PasswordChecker passwordChecker = new PasswordChecker();
        LengthCheck lengthCheck = new LengthCheck();
        UpperLetterCheck upperLetterCheck = new UpperLetterCheck();
        SpecialCharacterCheck specialCharacterCheck = new SpecialCharacterCheck();
        NumericCharacterCheck numericCharacterCheck = new NumericCharacterCheck();

        passwordChecker.nextCheck = lengthCheck;
        lengthCheck.nextCheck = upperLetterCheck;
        upperLetterCheck.nextCheck = specialCharacterCheck;
        specialCharacterCheck.nextCheck = numericCharacterCheck;

        passwordChecker.validatePassword(password);






    }
}
