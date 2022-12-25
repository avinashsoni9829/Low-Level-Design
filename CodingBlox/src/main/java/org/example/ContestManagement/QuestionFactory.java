package org.example.ContestManagement;

import org.example.utils.Difficulty;

import java.util.ArrayList;
import java.util.List;

public class QuestionFactory {
    private int question_count = 1;
    private Difficulty difficulty;
    private int qty;

    public QuestionFactory(Difficulty difficulty , int qty){
         this.difficulty = difficulty;
         this.qty = qty;
    }

    public List<Question> getQuestions(){
         List<Question> questions = new ArrayList<>();
         for(int i = 0 ; i < qty ; ++i){
            questions.add(new Question(getDescription(difficulty),difficulty,question_count));
            question_count+=1;
          }
         return  questions;
    }

    private String getDescription(Difficulty difficulty){

        switch (difficulty){
            case LOW: return  "a low level Question";

            case MEDIUM: return  "a medium level Question";

            case HARD: return  "a hard level Question";

            default: return  "not a valid Question";
        }
    }
}
