package com.example.cs2340a_team43.Models;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Leaderboard {
    //Following the Singleton Design Pattern for classes
    private static Leaderboard leaderboard; // The unique instance of the leaderboard.
    //AKA, the one and only leaderboard.
    private Leaderboard() {
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> queue = new PriorityQueue<Node>(5, comparator);

    }// Private constructor to prevent other classes from instantiating
    //our precious leaderboard.

    private class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node player1, Node player2) {
            //try {
                if (player1.getScore() < player2.getScore()) {
                    return 1;
                } else if (player1.getScore() > player2.getScore()) {
                    return -1;
                }
                return (player1.getTime().compareTo(player2.getTime()));
            //}
            //catch (NullPointerException playerNull) {
                //throw playerNull;
            //}
        }
    }

    //Public static getter method, so every class can look at the one leaderboard.
    public static Leaderboard getInstance() {
        if (leaderboard == null) {
            leaderboard = new Leaderboard(); // If there isn't a leaderboard yet, create one!
        }
        return leaderboard;
    }
}