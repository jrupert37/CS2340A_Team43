package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemyEyeball extends Enemy{
    public EnemyEyeball(String difficulty) {
        //this.movementBehavior = new RunMovement();
        setMovementBehavior(new WalkMovement());
        System.out.println("MOVEMENT BEHAVIOR SET");
        //System.out.println(getSpeed());
        imageId = R.drawable.eyeball;
        hp = 10;
        if (difficulty.equals("Easy")) {
            damage = 5;
        } else if (difficulty.equals("Medium")) {
            damage = 15;
        } else {
            damage = 25;
        }
    }
}
