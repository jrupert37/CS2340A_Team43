package com.example.cs2340a_team43.Models;

import androidx.core.util.Pair;

/*
 * This interface is used for both player and enemies
 */
public interface MovementBehavior {

    // helpful enumeration when calling move method
    public enum MovementDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN,
    }

    Pair<Integer, Integer> move(Pair<Integer, Integer> xy, MovementDirection direction);
    int getSpeed();
} // MovementBehavior (interface)
