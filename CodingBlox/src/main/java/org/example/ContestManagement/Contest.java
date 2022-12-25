package org.example.ContestManagement;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.UserManagement.User;
import org.example.utils.Difficulty;
import org.example.utils.Status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Contest {
    private int contest_count = 1;
    private  String     contestId;
    private  String     contestName;
    private  LocalDate  contestDate;
    private  LocalTime  contestTime;
    private  Difficulty difficulty;
    private  Status     contestStatus;
    private   int       no_of_questions;
    private List<Question> questionList;
    private User creator;

    public Contest(String contestName , Difficulty difficulty, User creator , int no_of_questions){
        this.contestId = "contest" + contest_count;
        contest_count = contest_count + 1;
        this.contestName = contestName;
        this.contestDate = LocalDate.now();
        this.contestTime = LocalTime.now();
        this.difficulty = difficulty;
        this.contestStatus = Status.CREATED;
        this.no_of_questions = no_of_questions;
        QuestionFactory questionFactory = new QuestionFactory(difficulty,no_of_questions);
        this.questionList = questionFactory.getQuestions();
        this.creator = creator;
    }


}
