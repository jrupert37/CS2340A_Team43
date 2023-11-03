package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemyGrimReaper extends Enemy{
    public EnemyGrimReaper(String difficulty) {
        imageId = R.drawable.grimreaper;
        hp = 50;
        if (difficulty.equals("Easy")) {
            damage = 10;
        } else if (difficulty.equals("Medium")) {
            damage = 15;
        } else {
            damage = 20;
        }
        movementBehavior = new WalkMovement();
    }
}
