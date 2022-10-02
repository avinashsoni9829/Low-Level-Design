package org.example.snake_ladder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dice {
      private Integer quantity;

      public int roll(){
           int min = 1 ;
           int max = 6 * quantity;
           double v = Math.random() * (max - min + 1) + min;
           return  (int)(v);
      }

}
