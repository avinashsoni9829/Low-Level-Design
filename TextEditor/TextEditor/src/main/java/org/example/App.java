package org.example;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println( "Hello World!" );
        Notepad notepad = new Notepad();

        do {
            System.out.println("Choose Your Option ! ");
            System.out.println("1. Display the text ");
            System.out.println("2. Display the text from given Lines");
            System.out.println("3. Insert the text");
            System.out.println("4. Delete the Text ");
            System.out.println("5. Delete the Text from the given lines");
            System.out.println("6. Copy ");
            System.out.println("7. Paste at given line");
            System.out.println("8. Undo your action ");
            System.out.println("9. Redo your action ");
            System.out.println("10. to exit");

            System.out.println("Enter your choice : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    notepad.display();
                    break;
                case 2:
                    int n, m;
                    System.out.println("Enter the Value of n and m");
                    n = scanner.nextInt();
                    m = scanner.nextInt();
                    notepad.display(n, m);
                    break;
                case 3:
                    System.out.println("enter text :");
                    String text = scanner.next();
                    int t;
                    System.out.println("enter the line number");
                    t = scanner.nextInt();
                    notepad.insertLine(t, text);
                    break;
                case 4:
                    System.out.println("enter the line to delete :");
                    int line = scanner.nextInt();
                    notepad.delete(line);
                    break;
                case 5:
                    int l, r;
                    System.out.println("enter the value of l ,r");
                    l = scanner.nextInt();
                    r = scanner.nextInt();
                    notepad.delete(l, r);
                    break;
                case 6:
                    int s, e;
                    System.out.println("enter the value of s ,e");
                    s = scanner.nextInt();
                    e = scanner.nextInt();
                    notepad.copy(s, e);
                    break;
                case 7:
                    System.out.println("enter the line to paste :");
                    int ln = scanner.nextInt();
                    notepad.paste(ln);
                    break;
                case 8:
                    notepad.undo();
                    break;
                case 9:
                    notepad.redo();
                    break;
                case 10:
                    System.exit(0);
                    break;
                default:
                    System.out.println("invalid choice ");
            }


        } while (true);

    }



}
