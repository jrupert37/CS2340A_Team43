package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemyEyeball extends Enemy {
    public EnemyEyeball(String difficulty) {
        super.setImageId(R.drawable.eyeball);
        super.setHP(10);
        if (difficulty.equals("Easy")) {
            super.setDamage(5);
        } else if (difficulty.equals("Medium")) {
            super.setDamage(15);
        } else {
            super.setDamage(25);
        }
    }
} // EnemyEyeball
