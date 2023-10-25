package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemyTwo extends Enemy{
    public EnemyTwo() {
        imageId = R.drawable.smiley;
        hp = 50;
        damage = 10;
        movementBehavior = new WalkMovement();
    }
}
