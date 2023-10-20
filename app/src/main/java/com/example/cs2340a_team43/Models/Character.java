package com.example.cs2340a_team43.Models;

import android.graphics.Rect;
import android.util.Pair;

import com.example.cs2340a_team43.Models.MovementBehavior.MovementDirection;

public abstract class Character {
    MovementBehavior movementBehavior;
    protected Pair<Integer, Integer> xyCoordinates;
    public Character () {
    }
    public Pair<Integer, Integer> performMove(Pair<Integer, Integer> xy, MovementDirection dir) {
        return movementBehavior.move(xy, dir);
    }
}
