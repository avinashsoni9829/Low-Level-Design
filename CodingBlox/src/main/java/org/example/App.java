package org.example;

import org.example.ApprRunner.AppRun;
import org.example.UserManagement.User;
import org.example.utils.Difficulty;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AppRun run = new AppRun();
        // Creating a User
       User u =  run.createUser("avinash","ason@gmail.com","123");
        // Creating Questions
        run.createQuestions(Difficulty.LOW,4);
        System.out.println();
        run.createQuestions(Difficulty.MEDIUM,5);
        System.out.println();
        // Showing Questions
        run.showQuestionsByDifficulty(Difficulty.MEDIUM);
        System.out.println();
        System.out.println();

        run.createContest("contest",Difficulty.MEDIUM,u,7);




    }
}
