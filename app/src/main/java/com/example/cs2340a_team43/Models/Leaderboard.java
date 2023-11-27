package com.example.cs2340a_team43.Models;

import java.util.Calendar;
import java.util.ArrayList;

/*
 * The Leaderboard class uses LeaderboardNodes to store each attempt at playing the game.
 * Leaderboard records the player's name, final score, start time, and end time.
 * An ArrayList stores each attempt, and the list is re-sorted by (descending) score
 * each time a game is finished.
 * This class follows the Singleton Design Pattern.
 */
public class Leaderboard {
    private static Leaderboard leaderboard; // the unique instance of the leaderboard
    private ArrayList<LeaderboardNode> leaderList; // to store each game attempt
    private LeaderboardNode mostRecentAttempt;
    private int size;

    /* Private constructor to prevent other classes from instantiating a new leaderboard */
    private Leaderboard() {
        leaderList = new ArrayList<>(5);
        this.size = 0;
        this.mostRecentAttempt = null;
    }

    public void addAttempt(String name, int score, Calendar startTime, Calendar endTime) {
        LeaderboardNode attempt = new LeaderboardNode(name, score, startTime, endTime);
        this.leaderList.add(attempt);
        this.leaderList.sort(LeaderboardNode.getNodeComparator()); // sort using custom comparator
        this.size++;
        setMostRecentAttempt(attempt);  // most recent attempt should be updated after each game
    }

    /* Public static getter method, so every class can access the single leaderboard instance */
    public static Leaderboard getInstance() {
        if (leaderboard == null) {
            leaderboard = new Leaderboard(); // if there isn't a leaderboard yet, create one!
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

    /* Clear method to empty leaderboard for testing purposes */
    public void clear() {
        this.leaderList = new ArrayList<>(5);
        this.size = 0;
        this.mostRecentAttempt = null;
    }
} // Leaderboard