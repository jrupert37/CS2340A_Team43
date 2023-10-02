package com.example.cs2340a_team43.Models;

public class Leaderboard {
    //Following the Singleton Design Pattern for classes
    private static Leaderboard leaderboard; // The unique instance of the leaderboard.
    //AKA, the one and only leaderboard.
    private Leaderboard() {} // Private constructor to prevent other classes from instantiating
    //our precious leaderboard.
    //TODO: Implement parameters and instance data into this contructor.

    //Public static getter method, so every class can look at the one leaderboard.
    public static Leaderboard getInstance() {
        if (leaderboard == null) {
            leaderboard = new Leaderboard(); // If there isn't a leaderboard yet, create one!
        }
        return leaderboard;
    }
}