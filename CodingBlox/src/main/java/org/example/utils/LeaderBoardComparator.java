package org.example.utils;

import org.example.ContestManagement.LeaderBoardEntity;

import java.util.Comparator;

public class LeaderBoardComparator implements Comparator<LeaderBoardEntity> {


    @Override
    public int compare(LeaderBoardEntity o1, LeaderBoardEntity o2) {
        if(o1.getScore() == o2.getScore()) return 0;
        else if(o1.getScore() > o2.getScore() )return  1;
        else
            return -1;

    }
}
