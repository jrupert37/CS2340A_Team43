package com.example.cs2340a_team43.Models;

import android.util.Pair;

public class WalkMovement implements MovementBehavior {
    public Pair<Integer, Integer> move(Pair<Integer, Integer> xy, MovementDirection direction) {
        int x;
        int y;
        if (xy.first == null) {
            x = 0;
        } else {
            x = xy.first;
        }

        if (xy.second == null) {
            y = 0;
        } else {
            y = xy.second;
        }

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
}
