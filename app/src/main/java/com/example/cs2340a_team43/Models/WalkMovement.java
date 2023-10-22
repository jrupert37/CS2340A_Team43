package com.example.cs2340a_team43.Models;

import android.util.Pair;

public class WalkMovement implements MovementBehavior {
    public Pair<Integer, Integer> move(Pair<Integer, Integer> xy, MovementDirection direction) {
        int x = xy.first;
        int y = xy.second;
        switch (direction) {
        case LEFT:
            x -= 1;
            break;
        case RIGHT:
            x += 1;
            break;
        case UP:
            y -= 1;
            break;
        case DOWN:
            y += 1;
            break;
        default:
            break;
        }
        return new Pair<>(x, y);
}
