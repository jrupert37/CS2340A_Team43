package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemyFour extends Enemy{
    public EnemyFour() {
        imageId = R.drawable.nerdplayersprite;
        hp = 10;
        damage = 5;
        movementBehavior = new RunMovement();
    }
}
