package org.example.ApprRunner;

import org.example.ContestManagement.*;
import org.example.UserManagement.User;
import org.example.utils.Difficulty;
import org.example.utils.LeaderBoardComparator;
import org.example.utils.Status;


import java.util.*;
import java.util.stream.Collectors;

public class AppRun  implements  AppFunctions {
    List<Contest> contestList;
    HashMap<String,Contest> contestDetails;
    List<Question> questionList;
    HashMap<String,User> userHashMap;

    HashMap<Contest , List<User>> contest_users;
    List<User> users;

    public AppRun(){
        contestList = new ArrayList<>();
        contestDetails = new HashMap<>();
        questionList = new ArrayList<>();
        users = new ArrayList<>();
        userHashMap = new HashMap<>();
        contest_users = new HashMap<>();
    }

    @Override
    public User createUser(String username, String email, String password) {
        User user = new User(username,email,password);
        users.add(user);
        userHashMap.put(user.getUserId(),user);
        System.out.println("User" + user.getUsername() + "with rating  = " + user.getRating() + "Created!");
        System.out.println(userHashMap);
        return user;
    }

    @Override
    public List<Question> createQuestions(Difficulty difficulty, int qty) {
        QuestionFactory qFactory = new QuestionFactory(difficulty,qty);
        List<Question> new_Q = qFactory.getQuestions();
        questionList.addAll(new_Q);
        System.out.println("new added Questions are " + new_Q);
        System.out.println("total Questions" + questionList);
        return new_Q;
    }

    @Override
    public void showQuestionsByDifficulty(Difficulty difficulty) {
        List<Question> list =  questionList.stream().filter(q -> Objects.equals(q.getDifficulty(),difficulty))
                .collect(Collectors.toList());
        System.out.println("Questions of difficulty = " + difficulty + " are = " + list);
    }

    @Override
    public Contest createContest(String contestName, Difficulty difficulty, User user, int q_qty) {
         Contest contest = new Contest(contestName,difficulty,user,q_qty);
         contestList.add(contest);
         contest_users.put(contest,new ArrayList<>());
         // Add Contest To User
         List<Contest> contests = userHashMap.get(user.getUserId()).getCreatedContests();
         contests.add(contest);
         user.setCreatedContests(contests);
         userHashMap.put(user.getUserId(),user);

         contestDetails.put(contest.getContestId(),contest);

        System.out.println("Contest Created Successfully");

         return contest;
    }

    @Override
    public Contest showContestDetails(String contestId) {
        return contestDetails.get(contestId);
    }


    @Override
    public void register(String contestId, User user) {
        // Adding User to the Contest
        Contest contest = showContestDetails(contestId);

        if(contest_users.containsKey(contest)){
            List<User> currUsers = contest_users.get(contest);
            currUsers.add(user);
            contest_users.put(contest,currUsers);
        }
        else
            contest_users.put(contest, Arrays.asList(user));

    }

    @Override
    public void withdraw(String contestId, User user) {

        Contest contest = showContestDetails(contestId);
        List<User> current_users = contest_users.get(contest);
        if(Objects.equals(contest.getContestStatus(),Status.CREATED)){
            // WithDraw From Contest
            current_users = current_users.stream().filter(u -> !Objects.equals(u,user)).collect(Collectors.toList());
            contest_users.put(contest,current_users);
            // Add To User History

            ContestParticipate participate = new ContestParticipate(contest,user);
            participate.setContestStatus(Status.WITHDRAWN);
            List<ContestParticipate> user_history = user.getHistory();
            user_history.add(participate);
            user.setHistory(user_history);

             System.out.println("SuccessFully Withdrawn");
        }
        else
        {
            System.out.println("Cannot Withdraw ! Either the Contest Has Started or Removed");
        }

    }

    @Override
    public void startContest(String contestId) {
        /**
         * Change the Contest Status To Started
         * Create a map of User, No of Questions Solved solved where solved questions are done randomly
         * Calculate the Points
         * Update the Ratings
         * Update the User
         * Create the LeaderBoard
         *
         */

         Contest contest = showContestDetails(contestId);
         contest.setContestStatus(Status.ONGOING);
         int size = contest_users.get(contest).size();
         Map<User,Integer> leaderBoardDetails = new HashMap<>();
         for(User u : contest_users.get(contestId)){
              leaderBoardDetails.put(u,getRandomNumber(0,size));

         }
         contest.setContestStatus(Status.COMPLETED);
         showLeaderBoard(leaderBoardDetails,contest.getDifficulty(),contest);
    }

    private void showLeaderBoard(Map<User, Integer> leaderBoardDetails,Difficulty d,Contest c) {
        List<LeaderBoardEntity> leaderboard = new ArrayList<>();
        leaderBoardDetails.forEach((key,value) ->{
         int score = d.getPayload() * value;
         int user_rating = key.getRating();
         int final_payload  = getUpdatedRating(d,score);
        leaderboard.add(new LeaderBoardEntity(key,user_rating + final_payload));
        });

        leaderboard.sort(new LeaderBoardComparator());
        int rank = 1;
        for(LeaderBoardEntity entity : leaderboard){
            System.out.println("rank - " + rank);
            System.out.println("rating - " +entity.getScore());
            // Adding to Contest Participate

            ContestParticipate participate = new ContestParticipate();
            participate.setInitial_rating(entity.getUser().getRating());
            participate.setFinal_rating(entity.getScore());
            participate.setParticipationStatus(Status.COMPLETED);
            participate.setSolvedQuestions(leaderBoardDetails.get(entity.getUser()));
            participate.setRank(rank);
            // Updating user
            User u = userHashMap.get(entity.getUser().getUserId());
            List<ContestParticipate> participates = u.getHistory();
            participates.add(participate);
            u.setHistory(participates);
            userHashMap.put(u.getUserId(),u);

            rank = rank + 1;
        }

    }

    private int getUpdatedRating(Difficulty d, int score) {
        switch (d){
            case LOW: return score - 50;
            case MEDIUM: return score - 20;
            case HARD: return  score ;
        }
        return 0;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
