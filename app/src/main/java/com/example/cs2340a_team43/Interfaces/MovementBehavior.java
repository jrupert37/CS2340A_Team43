package com.example.cs2340a_team43.Interfaces;

import com.example.cs2340a_team43.Models.XYPair;

/*
 * This interface is used for both player and enemies
 */
public interface MovementBehavior {

    // helpful enumeration when calling move method
    enum MovementDirection {
        LEFT,
        RIGHT,
        UP,
        DOWN,
    }

    void move(XYPair xy, MovementDirection direction);
    int getSpeed();
} // MovementBehavior (interface)
