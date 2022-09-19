package org.example.TicTacToe.gameManagement;
import lombok.Data;


@Data

public class GameBoard  implements  GameBoardFeature {
    private int boardSize;

    private int moves = 0;

    private  EventType situation = EventType.ONGOING;
    private Character [][] board ;

   public GameBoard(int size){
         boardSize = size;

    }


    public void initialize(){
        board = new Character[boardSize + 1][boardSize + 1];
        for(int i = 0 ; i <boardSize ; ++i){
             for(int j = 0 ; j < boardSize ; ++j){

                 board[i][j] = Symbol.START.getValue();

             }
        }
    }

    @Override
    public void display(){
        for(int i = 0 ; i <boardSize ; ++i){
            for(int j = 0 ; j < boardSize ; ++j){

                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
