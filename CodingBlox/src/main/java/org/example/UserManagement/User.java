package org.example.UserManagement;

import lombok.Data;
import org.example.ContestManagement.Contest;
import org.example.ContestManagement.ContestParticipate;

import java.util.ArrayList;
import java.util.List;


@Data
public class User {
    private int user_count = 1 ;
    private String userId;
    private String username;
    private String email;
    private String password;
    // Initially Rating will be 1500
    private int rating;

    private List<Contest> createdContests;

    private List<ContestParticipate> history;



     public User(String username, String email, String password){
          this.userId = "user" + user_count ;
          this.rating = 1500;
          user_count = user_count + 1;
          this.createdContests = new ArrayList<>();
          this.history = new ArrayList<>();
      }

}
