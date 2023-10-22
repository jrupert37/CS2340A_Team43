package com.example.cs2340a_team43.Models;

import android.util.Pair;

public class RunMovement implements MovementBehavior{
    public Pair<Integer, Integer> move(Pair<Integer, Integer> xy, MovementBehavior.MovementDirection direction) {
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
