package org.example.TicTacToe.gameManagement;

import lombok.Data;

@Data
public class Game implements GameFlow {
    private  Board gameBoard;

    public Game(Board gameBoard){
         this.gameBoard = gameBoard;
    }
    public void initialSetUp(){
         // initialize the board
         gameBoard.getBoard().initialize();
         gameBoard.getBoard().display();
    }

    public void announceWinner(){
          gameBoard.getUserChoices().poll();
          Player p = gameBoard.getUserChoices().poll();
          System.out.println("Winner is - " + p.getGameName());;

    }

    @Override
    public void run() {
             initialSetUp();
             int size = gameBoard.getBoard().getBoardSize();

             while(gameBoard.getBoard().getMoves() < size * size){

                 if(gameBoard.getBoard().getSituation().equals(EventType.WIN)){
                      announceWinner();
                      break;
                 }
                 System.out.println("Game Situation = "+ gameBoard.getBoard().getSituation());


                  gameBoard.MakeMove();

                  System.out.println("======== BOARD ========");

                  gameBoard.getBoard().display();

                 System.out.println("======== BOARD ========");
             }
             if(gameBoard.getBoard().getMoves() == gameBoard.getBoard().getBoardSize() * gameBoard.getBoard().getBoardSize()){
                 gameBoard.judge();
                 if(gameBoard.getBoard().getSituation().equals(EventType.WIN)){
                     announceWinner();
                 }
                 else if(gameBoard.getBoard().getSituation().equals(EventType.DRAW)){
                     System.out.println(EventType.DRAW.getValue());
                 }
             }

    }


}
