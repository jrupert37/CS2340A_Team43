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
    private int size;
    private Node mostRecentAttempt;
    private Leaderboard() {
        leaderList = new ArrayList<Node>(5);
        this.size = 0;
    } // Private constructor to prevent other classes from instantiating
    // our precious leaderboard.

    public void addAttempt(String name, int score, Calendar startTime, Calendar endTime) {
        Node attempt = new Node(name, score, startTime, endTime);
        this.leaderList.add(attempt);
        this.leaderList.sort(Node.getNodeComparator());
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

    public Node get(int index) {
        return this.leaderList.get(index);
    }

    public int getSize() {
        return this.size;
    }

    private void setMostRecentAttempt(Node attempt) {
        this.mostRecentAttempt = attempt;
    }

    public Node getMostRecentAttempt() {
        return this.mostRecentAttempt;
    }

//    public Node[] getArray() {
//        Node[] array = this.leaderList.toArray(new Node[0]);
//        Arrays.sort(array, Node.getNodeComparator()); // sort list according to NodeComparator
//        return array;
//    }

} // class Leaderboard