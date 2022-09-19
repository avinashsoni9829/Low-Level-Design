package org.example;

import org.example.TicTacToe.gameManagement.Board;
import org.example.TicTacToe.gameManagement.EventType;
import org.example.TicTacToe.gameManagement.Game;
import org.example.TicTacToe.gameManagement.GameBoard;

/**
 * Hello world!
 *
 */
public class TicTacToeGame
{
    public static void main( String[] args )
    {
        GameBoard board = new GameBoard(3);
        Board ticTacToe = new Board(board);
        Game game = new Game(ticTacToe);
        game.run();
    }


}
