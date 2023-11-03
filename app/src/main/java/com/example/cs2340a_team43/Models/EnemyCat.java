package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;
public class EnemyCat extends Enemy{
    public EnemyCat(String difficulty) {
        imageId = R.drawable.catenemy;
        hp = 50;
        if (difficulty.equals("Easy")) {
            damage = 5;
        } else if (difficulty.equals("Medium")) {
            damage = 10;
        } else {
            damage = 15;
        }
        movementBehavior = new WalkMovement();
    }
}
