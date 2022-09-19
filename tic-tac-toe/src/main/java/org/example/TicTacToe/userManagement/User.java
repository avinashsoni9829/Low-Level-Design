package org.example.TicTacToe.userManagement;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    // Builder Pattern Can be Used to Initialize the User Object
    private String gameId;
    private PersonalDetails personalDetails;
    private Statistics stats;

}
