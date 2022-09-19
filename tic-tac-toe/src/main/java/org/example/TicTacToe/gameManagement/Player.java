package org.example.TicTacToe.gameManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.TicTacToe.userManagement.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Player {
     private User userDetails;
     private String gameName;
     private Symbol userSymbol;



}
