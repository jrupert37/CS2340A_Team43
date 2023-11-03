package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemyTwo extends Enemy{
    public EnemyTwo(String difficulty) {
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
