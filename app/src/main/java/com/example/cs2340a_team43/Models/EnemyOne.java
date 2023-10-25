package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;
public class EnemyOne extends Enemy{
    public EnemyOne() {
        imageId = R.drawable.frowny;
        hp = 50;
        damage = 5;
        movementBehavior = new RunMovement();
    }
}
