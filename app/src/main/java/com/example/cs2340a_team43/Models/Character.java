package com.example.cs2340a_team43.Models;

import androidx.core.util.Pair;
import com.example.cs2340a_team43.Models.MovementBehavior.MovementDirection;

/*
* This is a parent class for any Character type, including player and enemies
*/
public abstract class Character {
    private MovementBehavior movementBehavior;
    private Pair<Integer, Integer> xyCoordinates;

    protected Character() {
        this.movementBehavior = new WalkMovement(); // set to WalkMovement by default
        this.xyCoordinates = new Pair<>(0, 0);      // set to (0,0) by default
    }

    //    public Pair<Integer, Integer> performMove(Pair<Integer, Integer> xy, MovementDirection
    //    dir) {
    //        return movementBehavior.move(xy, dir);
    //    }

    public void performMove(MovementDirection dir) {
        setXyCoordinates(movementBehavior.move(this.xyCoordinates, dir));
    }

    public void setMovementBehavior(MovementBehavior behavior) {
        this.movementBehavior = behavior;
    }

    public int getSpeed() {
        return this.movementBehavior.getSpeed();
    }

    public int getX() {
        return this.xyCoordinates.first;
    }

    public  int getY() {
        return this.xyCoordinates.second;
    }

    public void setInitialXY(int x, int y) {
        this.xyCoordinates = new Pair<>(x, y);
    }

    private void setXyCoordinates(Pair<Integer, Integer> xyCoordinates) {
        this.xyCoordinates = xyCoordinates;
    }

} // Character (abstract parent)
