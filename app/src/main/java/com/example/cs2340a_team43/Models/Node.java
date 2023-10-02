package com.example.cs2340a_team43.Models;

import java.util.Date;

public class Node {
    //NOT a singleton class, because we want multiple Nodes in the leaderboard!
    private String name;
    private int score;
    private Date time;

    public Node (String name, int score, Date time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    public String getName() {
        return name;
    }
    public void setName(String newName) {
        name = newName;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int newScore) {
        score = newScore;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date newTime) {
        time = newTime;
    }
}
