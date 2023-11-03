package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemySkeleton extends Enemy {
    public EnemySkeleton(String difficulty) {
        imageId = R.drawable.skeletonenemy;
        hp = 100;
        if (difficulty.equals("Easy")) {
            damage = 1;
        } else if (difficulty.equals("Medium")) {
            damage = 3;
        } else {
            damage = 5;
        }
        movementBehavior = new WalkMovement();
    }
}
