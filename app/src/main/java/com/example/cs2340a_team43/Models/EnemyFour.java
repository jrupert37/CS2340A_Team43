package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemyFour extends Enemy{
    public EnemyFour(String difficulty) {
        imageId = R.drawable.nerdplayersprite;
        hp = 10;
        if (difficulty.equals("Easy")) {
            damage = 5;
        } else if (difficulty.equals("Medium")) {
            damage = 15;
        } else {
            damage = 25;
        }
        movementBehavior = new RunMovement();
    }
}
