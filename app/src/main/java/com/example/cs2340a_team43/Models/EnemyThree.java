package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemyThree extends Enemy {
    public EnemyThree() {
        imageId = R.drawable.medium_face;
        hp = 100;
        damage = 1;
        movementBehavior = new WalkMovement();
    }
}
