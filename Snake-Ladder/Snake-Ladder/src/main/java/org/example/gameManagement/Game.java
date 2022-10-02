package org.example.gameManagement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.snake_ladder.BoardGenerator;
import org.example.snake_ladder.Cell;
import org.example.snake_ladder.CellType;
import org.example.snake_ladder.Dice;
import org.example.snake_ladder.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;






public class Game implements GameFeatures{

    private BoardGenerator boardGenerator;

    private GameState state = GameState.START;
    private Dice dice;
    Queue<Player> players = new LinkedList<>();

    Map<Player,Integer>playerMap = new HashMap<>();

    Scanner scn = new Scanner(System.in);

    public Game(BoardGenerator boardGenerator, Dice dice) {
        this.boardGenerator = boardGenerator;
        this.dice = dice;

    }


    @Override
    public void initializeGame() {
        boardGenerator.initialize();
        askPlayers();
        getDice();
        System.out.println("Starting the game now!");
   }




    @Override
    public void askPlayers() {
     System.out.println("How Many Player are playing ?");
     int count = scn.nextInt();
        System.out.println("Count = " + count);
     String name;
     for(int i = 1 ; i <= count  ; i++){
          name = "Player" + i;
          Player p = new Player((i),name);
          players.add(p);
          playerMap.put(p,1);
     }

    }



    void runChance(Player p , int steps){
          state = GameState.ONGOING;
          int currPosition = playerMap.get(p);
          int nextPosition = currPosition + steps;
        if(nextPosition >= boardGenerator.getSize()){
            state = GameState.END;
            System.out.println("Winner is " + p.getName());
            return;
        }


        // Updating Postiton as Per board
           Cell c = boardGenerator.getBoard()[nextPosition - 1];

          CellType cd = boardGenerator.getBoardDescription()[nextPosition - 1];

          if(!c.equals(Cell.NORMAL)){

              System.out.println("You Are on a " + cd.getName());
              System.out.println("You have moved from " + nextPosition + " to " + cd.getEnd());
              nextPosition =  cd.getEnd();
          }

          playerMap.put(p,nextPosition);
          if(nextPosition >= boardGenerator.getSize()){
            state = GameState.END;
            System.out.println("Winner is " + p.getName());
            return;
          }

          players.add(p);


    }

    void rollDice(){
        Player curr = players.poll();
        int steps = dice.roll();
        System.out.println("Steps = " + steps);
        runChance(curr,steps);
    }


    @Override
    public void getDice() {
        System.out.println("How Many Dice You need ?");
        int count = scn.nextInt();
        dice = new Dice(count);
    }

    @Override
    public void run() {



        System.out.println("Game Begins");

        while (!(state.equals(GameState.END))){
               rollDice();
        }

        System.out.println("Game Has Ended!");
        return;
    }


}
