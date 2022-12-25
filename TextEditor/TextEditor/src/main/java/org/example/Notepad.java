package org.example;

import lombok.Data;


import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.LinkedList;

import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

@Data
public class Notepad {
    private  int id = 1;
    private  static  int capacity = 100;
    private String [] notepad = new String[capacity] ;
    private  Stack<Action> UndoActions;
    private  Stack<Action> RedoActions;

    private Queue<String> clipBoard;

    public  Notepad(){
        for(int i = 0 ; i < capacity; ++i){
            notepad[i]="";
        }
        UndoActions = new Stack<>();
        RedoActions = new Stack<>();
        clipBoard = new LinkedList<>();
    }

    void insertLine(int n,String text){

        if(n > capacity){
            throw new RuntimeException("notepad limit crossed!");
        }
        notepad[n-1]=text;
        UndoActions.push(new Action(id++, LocalDateTime.now(),true,n,text));
    }


    void display(){
        Arrays.stream(notepad).forEach(System.out::println);
    }


    void display(int n, int m){
        if(m > capacity || n > m){
            throw new RuntimeException("unable to display! please check the constraints or the input");
        }
        for(int i = n - 1;  i < m ; ++i) System.out.println(notepad[i]);
    }


    void delete(int n){
         if(n > capacity){
             throw new RuntimeException("there is no such row to delete!");
         }
         // remove
         if(Objects.nonNull(notepad[n]) && !notepad[n-1].isEmpty()){
             Action action = new Action(id++,LocalDateTime.now(),false,n,notepad[n-1]);
             RedoActions.push(action);
             notepad[n-1] ="";
         }
        display();
     }


     void delete(int n , int m){
        if(n > m){
            throw new RuntimeException("unable to delete! please check the constraints or the input");
        }

        for(int i = n; i < m ; ++i){
           delete(i);
        }
         display();
     }

     void copy(int n , int m){
         if( m > capacity || n > m){
             throw new RuntimeException("unable to copy! out of bounds");
         }
         String copyText = "";
         for(int i = n  ; i < m ; ++i){
             copyText = copyText + notepad[i];
         }
         if(!copyText.isEmpty())
         clipBoard.add(copyText);
     }

     void paste(int n){
        if(clipBoard.isEmpty()){
            throw new RuntimeException("nothing to paste!");
        }
        String text = clipBoard.peek();
        insertLine(n,text);
         display();
     }

     void undo(){
         if(UndoActions.isEmpty()){
             throw new RuntimeException("Nothing to Undo");
         }

         Action action = UndoActions.peek();
         UndoActions.pop();
         delete(action.getLineNumber());


         display();
     }

     void redo(){
         if(RedoActions.isEmpty()){
             throw new RuntimeException("Nothing to Redo");
         }
         Action action = RedoActions.peek();
         RedoActions.pop();
         insertLine(action.getLineNumber(),action.getText());
         display();
     }


}
