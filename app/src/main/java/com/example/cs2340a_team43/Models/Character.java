package com.example.cs2340a_team43.Models;

import androidx.core.util.Pair;

import com.example.cs2340a_team43.Models.MovementBehavior.MovementDirection;

public abstract class Character {
    protected MovementBehavior movementBehavior;
    protected Pair<Integer, Integer> xyCoordinates = new Pair<>(0, 0);
    public Character() {
    }
    public Pair<Integer, Integer> performMove(Pair<Integer, Integer> xy, MovementDirection dir) {
        return movementBehavior.move(xy, dir);
    }
    public void setMovementBehavior(MovementBehavior behavior) {
        this.movementBehavior = behavior;
    }
    public int getSpeed() {
        return this.movementBehavior.getSpeed();
    }
}
