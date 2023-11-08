package com.example.cs2340a_team43.Models;

import androidx.core.util.Pair;

/*
 * Represents a type of MovementBehavior, used by player and enemies.
 * WalkMovement indicates a player or enemy can move only one tile at a time.
 * WalkMovement does NOT control how quickly a character can visually move
 * across the screen.
 */
public class WalkMovement implements MovementBehavior {

    /*
     * In this case, "speed" does not refer to how quickly a character can
     * visually move across the screen, but how many tiles the character
     * can move at one time.
     */
    private final int speed = 1;
    public Pair<Integer, Integer> move(Pair<Integer, Integer> xy, MovementDirection direction) {
        int x = xy.first;
        int y = xy.second;
        switch (direction) {
        case LEFT:
            x -= speed;
            break;
        case RIGHT:
            x += speed;
            break;
        case UP:
            y -= speed;
            break;
        case DOWN:
            y += speed;
            break;
        default:
            break;
        }
        return new Pair<>(x, y);
    }

    /* This method is useful for detecting wall collisions */
    public int getSpeed() {
        return this.speed;
    }
} // WalkMovement
