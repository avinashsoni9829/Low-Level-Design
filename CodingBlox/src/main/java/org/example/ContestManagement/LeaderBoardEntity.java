package org.example.ContestManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.UserManagement.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderBoardEntity {
    private User user;
    private int score;
}
