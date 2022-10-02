package org.example;

import org.example.gameManagement.Game;
import org.example.snake_ladder.BoardGenerator;
import org.example.snake_ladder.Dice;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Game game = new Game(new BoardGenerator(100),new Dice());
        game.initializeGame();
        game.run();

    }
}
