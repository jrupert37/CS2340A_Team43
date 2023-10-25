package com.example.cs2340a_team43.Models;


import androidx.core.util.Pair;

public class RunMovement implements MovementBehavior {
    final int speed = 2;
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
    public int getSpeed() {
        return this.speed;
    }
}
