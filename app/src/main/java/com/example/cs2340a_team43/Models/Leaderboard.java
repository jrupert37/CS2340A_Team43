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
    //TODO: Implement parameters and instance data into this constructor.

    private class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node player1, Node player2) {
            //Assumes neither Node is null (for now)
            if (player1.getScore() < player2.getScore()) {
                return 1;
            }
            else if (player1.getScore() > player2.getScore()) {
                return -1;
            }
            else if (player1.getScore() == player2.getScore()) {
                //if (player1.getTime() < player2.getTime()) {
                return -1;
                //}
            }
            return 0; //for now
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