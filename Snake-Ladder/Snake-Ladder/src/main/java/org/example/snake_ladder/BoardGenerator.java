package org.example.snake_ladder;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Data
@NoArgsConstructor
public class BoardGenerator implements  BoardGeneratorFeatures {

    private Scanner scn = new Scanner(System.in);
    private int size;
    private List<CellType> cells ;
    private CellType [] boardDescription ;
    private Cell []  board ;


    public BoardGenerator(int size){
         this.size = size;
         cells = new ArrayList<>();
         boardDescription = new CellType[size];
         board = new  Cell[size];
    }

    public  void initialize(){

        // Generate the Additives
        generateInstances(true);
        // Generate the Subtractives
        generateInstances(false);


        System.out.println(board.length);
        // initialize all the board with normal | Start | end
        board[0] = Cell.START;
        board[size - 1]  = Cell.END;

        for(int i = 1 ; i < size - 1 ; ++i){
             board[i] = Cell.NORMAL;
        }

        // update boardDescription with cells and the board with appropriate values as well

        for(CellType cell : cells)
        {
             boardDescription[cell.getStart()] = cell;
             board[cell.getStart()] =  cell.getIsAdditive() ? Cell.ADD : Cell.SUB;
        }


    }

    public  void ask(Boolean flag){
         String type  = flag ? "Additive" : "Subtractive";
         System.out.println("Welcome! Lets Create a  " + type );
         System.out.println("1. To create" + type);
         System.out.println("2. To Stop the Process");
         System.out.println("Enter Your Choice : ");
    }


    public void askSize(List<EntityType>size){
         for(int i = 0 ; i < size.size() ; ++i){
              System.out.println((i + 1) + "." + size.get(i));
         }
        System.out.println("Enter Your Choice : ");
    }
    public void createCellInstance(Boolean flag){
        String type = flag ? "ADDITIVE" : "SUBTRACTIVE";
        System.out.println("You can create the Following " + type);
        List<Object>list = Arrays.asList(flag ? ADDITIVE.values() : SUBTRACTIVE.values());
        for(int i = 0 ; i < list.size() ; ++i){
             System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println("Enter Your Choice : ");
        int ch = scn.nextInt();
        System.out.println("Enter the Location Where you want to Place it  [0 to  " + (size -  1) + " ] ");
        int start = scn.nextInt();

        // We are assuming that the user input is as per our validation conditions
        List<EntityType> sz = Arrays.asList(EntityType.values());
        askSize(sz);
        int choice = scn.nextInt();
        double end = flag ? (Math.ceil(size * sz.get(choice - 1).getValue()) + start) : (start - Math.ceil(size * sz.get(choice - 1).getValue()));
        CellType cn ;
        if(flag){
            ADDITIVE instance = (ADDITIVE) list.get(ch-1);
            cn = new CellType(true,instance.getName(),start,(int)end);
        }
        else{
             SUBTRACTIVE instance = (SUBTRACTIVE) list.get(ch-1);
             cn = new CellType(false, instance.getName(),start,(int)end);
        }
        cells.add(cn);

    }

    public void  performChoice(int choice , Boolean flag){
         switch (choice){
             case  1: createCellInstance(flag);
                      break;
             default: System.out.println("Your choice is Not Valid!");

         }

    }

    public void generateInstances(Boolean flag){
         while(true){
              ask(flag);
              int ch = scn.nextInt();
              if(ch == 2) break;
              performChoice(ch , flag);
         }
    }

   
}
