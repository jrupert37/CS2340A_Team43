package com.example.cs2340a_team43.Models;


import java.util.Calendar;
import java.util.ArrayList;

public class Leaderboard {
    //Following the Singleton Design Pattern for classes
    private static Leaderboard leaderboard; // The unique instance of the leaderboard.
    //AKA, the one and only leaderboard.
    private ArrayList<LeaderboardNode> leaderList;
    private int size;
    private LeaderboardNode mostRecentAttempt;
    private Leaderboard() {
        leaderList = new ArrayList<LeaderboardNode>(5);
        this.size = 0;
        this.mostRecentAttempt = null;
    } // Private constructor to prevent other classes from instantiating
    // our precious leaderboard.

    public void addAttempt(String name, int score, Calendar startTime, Calendar endTime) {
        LeaderboardNode attempt = new LeaderboardNode(name, score, startTime, endTime);
        this.leaderList.add(attempt);
        this.leaderList.sort(LeaderboardNode.getNodeComparator());
        this.size++;
        setMostRecentAttempt(attempt);  // most recent attempt should be updated each time
        // a new game attempt is added to leaderboard list
    }

    //Public static getter method, so every class can look at the one leaderboard.
    public static Leaderboard getInstance() {
        if (leaderboard == null) {
            leaderboard = new Leaderboard(); // If there isn't a leaderboard yet, create one!
        }
        return leaderboard;
    }

    public LeaderboardNode get(int index) {
        return this.leaderList.get(index);
    }

    public int getSize() {
        return this.size;
    }

    private void setMostRecentAttempt(LeaderboardNode attempt) {
        this.mostRecentAttempt = attempt;
    }

    public LeaderboardNode getMostRecentAttempt() {
        return this.mostRecentAttempt;
    }

    // clear method to empty leaderboard instance for testing purposes
    public void clear() {
        this.leaderList = new ArrayList<>(5);
        this.size = 0;
        this.mostRecentAttempt = null;
    }

} // class Leaderboard