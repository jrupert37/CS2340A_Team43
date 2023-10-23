package com.example.cs2340a_team43.Models;


import androidx.core.util.Pair;

public class RunMovement implements MovementBehavior {
    public Pair<Integer, Integer> move(Pair<Integer, Integer> xy, MovementDirection direction) {
        int x = xy.first;
        int y = xy.second;
        switch (direction) {
        case LEFT:
            x -= 2;
            break;
        case RIGHT:
            x += 2;
            break;
        case UP:
            y -= 2;
            break;
        case DOWN:
            y += 2;
            break;
        default:
            break;
        }
        return new Pair<>(x, y);
    }
}
