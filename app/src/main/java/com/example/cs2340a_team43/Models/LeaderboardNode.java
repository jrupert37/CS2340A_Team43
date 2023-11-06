package com.example.cs2340a_team43.Models;

import java.util.Calendar;
import java.util.Comparator;

/*
 * This node class is used by the leaderboard. A node holds a player's name, score,
 * start time, and end time.
 * This class also contains a singleton custom comparator useful for sorting a list
 * of leaderboard nodes based on score.
 * This class is not singleton, since we want to have multiple nodes in the leaderboard.
 */
public class LeaderboardNode {
    private final String name;
    private final int score;
    private final Calendar startTime;
    private final Calendar endTime;
    private static NodeComparator nodeComparator;

    LeaderboardNode(String name, int score, Calendar startTime, Calendar endTime) {
        this.name = name;
        this.score = score;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public Calendar getStartTime() {
        return this.startTime;
    }


    public Calendar getEndTime() {
        return this.endTime;
    }

    /* Custom toString method for converting a Calendar into a useful, readable String.
    *  Return value will be of the format "DayOfWeek mm/dd/yyyy hour:min:sec".
    */
    public String toString(String key) {
        // the calendar in question my either be the player's start time OR end time
        Calendar calendar = getStartTime();
        if (key.equals("end")) {
            calendar = getEndTime();
        }

        String[] days = {"Sun", "Mon", "Tues", "Wed", "Thu", "Fri", "Sat"};

        String dayOfWeek = days[calendar.get(Calendar.DAY_OF_WEEK) - 1];

        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        String monthDayYear = month + "/" + day + "/" + year;

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        String min = minute < 10 ? "0" + minute : Integer.toString(minute);
        String sec = second < 10 ? "0" + second : Integer.toString(second);
        String hrMinSec = hour + ":" + min + ":" + sec;

        return dayOfWeek + " " + monthDayYear + " " + hrMinSec;
    }

    /* Static getter method, so that Leaderboard class can access the node comparator */
    static NodeComparator getNodeComparator() {
        if (nodeComparator == null) {
            nodeComparator = new NodeComparator();
        }
        return nodeComparator;
    }

    /*
     * Custom comparator class useful for sorting a list of LeaderboardNodes
     * based on score. If the two scores match, player with the earlier start
     * time is sorted "higher" in the list. Two start times cannot and will not match.
     */
    private static class NodeComparator implements Comparator<LeaderboardNode> {
        @Override
        public int compare(LeaderboardNode player1, LeaderboardNode player2) {
            if (player1.getScore() < player2.getScore()) {
                return 1;
            } else if (player1.getScore() > player2.getScore()) {
                return -1;
            // if player scores match, sort based on start time, which cannot be equal
            } else if (player1.getScore() == player2.getScore()) {
                if (player1.getStartTime().compareTo(player2.getStartTime()) < 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        }
    } // NodeComparator
} // Node