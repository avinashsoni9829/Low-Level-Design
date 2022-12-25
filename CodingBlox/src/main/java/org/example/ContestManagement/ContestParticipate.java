package org.example.ContestManagement;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.UserManagement.User;
import org.example.utils.Difficulty;
import org.example.utils.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@Data
public class ContestParticipate extends  Contest{
   private int rank;
   private int  solvedQuestions;
   private Status participationStatus;
   private int initial_rating;
   private int final_rating;

   public ContestParticipate(Contest contest, User creator) {
      super();

   }
}
