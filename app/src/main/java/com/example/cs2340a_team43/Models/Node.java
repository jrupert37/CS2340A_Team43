package com.example.cs2340a_team43.Models;

import java.util.Calendar;
import java.util.Comparator;

public class Node {
    //NOT a singleton class, because we want multiple Nodes in the leaderboard!
    private String name;
    private int score;
    private Calendar startTime;
    private Calendar endTime;
    private static NodeComparator nodeComparator;


    public Node(String name, int score, Calendar startTime, Calendar endTime) {
        this.name = name;
        this.score = score;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String newName) {
        name = newName;
    }
    public int getScore() {
        return this.score;
    }
    public void setScore(int newScore) {
        score = newScore;
    }

    public Calendar getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Calendar newTime) {
        startTime = newTime;
    }

    public Calendar getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Calendar newTime) {
        endTime = newTime;
    }

    public String startTimeToString() {
        String[] days = {"Sun ", "Mon ", "Tues ", "Wed ", "Thu ", "Fri ", "Sat "};
        String dayOfWeek = days[getStartTime().get(Calendar.DAY_OF_WEEK) - 1];

        int month = getStartTime().get(Calendar.MONTH) + 1;
        int day = getStartTime().get(Calendar.DAY_OF_MONTH);
        int year = getStartTime().get(Calendar.YEAR);

        int startHour = getStartTime().get(Calendar.HOUR) + 1;
        int startMinute = getStartTime().get(Calendar.MINUTE);
        int startSecond = getStartTime().get(Calendar.SECOND);

        String monthDayYear = month + "/" + day + "/" + year + " ";

        String startMin = startMinute < 10 ? "0" + startMinute : Integer.toString(startMinute);
        String startSec = startSecond < 10 ? "0" + startSecond : Integer.toString(startSecond);
        String startTime = startHour + ":" + startMin + ":" + startSec;

        return dayOfWeek + monthDayYear + startTime;
    }

    public String endTimeToString() {
        String[] days = {"Sun ", "Mon ", "Tues ", "Wed ", "Thu ", "Fri ", "Sat "};
        String dayOfWeek = days[getStartTime().get(Calendar.DAY_OF_WEEK) - 1];

        int month = getStartTime().get(Calendar.MONTH) + 1;
        int day = getStartTime().get(Calendar.DAY_OF_MONTH);
        int year = getStartTime().get(Calendar.YEAR);

        int endHour = getEndTime().get(Calendar.HOUR) + 1;
        int endMinute = getEndTime().get(Calendar.MINUTE);
        int endSecond = getEndTime().get(Calendar.SECOND);

        String monthDayYear = month + "/" + day + "/" + year + " ";

        String endMin = endMinute < 10 ? "0" + endMinute : Integer.toString(endMinute);
        String endSec = endSecond < 10 ? "0" + endSecond : Integer.toString(endSecond);
        String endTime = endHour + ":" + endMin + ":" + endSec;

        return dayOfWeek + monthDayYear + endTime;
    }

    public static NodeComparator getNodeComparator() {
        if (nodeComparator == null) {
            nodeComparator = new NodeComparator();
        }
        return nodeComparator;
    }
    private static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node player1, Node player2) {
            //Assumes neither Node is null (for now)
            if (player1.getScore() < player2.getScore()) {
                return 1;
            } else if (player1.getScore() > player2.getScore()) {
                return -1;
            } else if (player1.getScore() == player2.getScore()) {
                if (player1.getStartTime().compareTo(player2.getStartTime()) < 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0; //for now
        }
    } // class NodeComparator
} // class Node
