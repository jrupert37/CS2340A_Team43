package com.example.cs2340a_team43.Models;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Calendar;
import java.util.ArrayList;

public class Leaderboard {
    //Following the Singleton Design Pattern for classes
    private static Leaderboard leaderboard; // The unique instance of the leaderboard.
    //AKA, the one and only leaderboard.
    private ArrayList<Node> leaderList;
    private Node mostRecentAttempt;
    private Leaderboard() {
        leaderList = new ArrayList<Node>(5);
    } // Private constructor to prevent other classes from instantiating
    //our precious leaderboard.
    //TODO: Implement parameters and instance data into this constructor.

    public void addGame(String name, int score, Calendar startTime, Calendar endTime) {
        Node attempt = new Node(name, score, startTime, endTime);
        leaderList.add(attempt);
        Node[] leaders = getArray();
        for (int i = 0; i < leaders.length; i++) {
            System.out.println(leaders[i].getScore());
        }
        setMostRecentAttempt(attempt);
    }

    //Public static getter method, so every class can look at the one leaderboard.
    public static Leaderboard getInstance() {
        if (leaderboard == null) {
            leaderboard = new Leaderboard(); // If there isn't a leaderboard yet, create one!
        }
        return leaderboard;
    }

    private void setMostRecentAttempt(Node attempt) {
        this.mostRecentAttempt = attempt;
    }

    public Node getMostRecentAttempt() {
        return this.mostRecentAttempt;
    }

    public Node[] getArray() {
        Node[] array = this.leaderList.toArray(new Node[0]);
        Arrays.sort(array, Node.getNodeComparator());
        return array;
    }

    // custom comparator class for comparing player scores

} // class Leaderboard