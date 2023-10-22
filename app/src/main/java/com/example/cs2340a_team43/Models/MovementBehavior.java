package com.example.cs2340a_team43.Models;

import android.util.Pair;

public interface MovementBehavior {
    public enum MovementDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        NONE
    }
    abstract Pair<Integer, Integer> move(Pair<Integer, Integer> xy, MovementDirection direction);
}
