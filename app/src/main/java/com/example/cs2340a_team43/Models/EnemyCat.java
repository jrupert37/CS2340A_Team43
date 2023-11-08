package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemyCat extends Enemy {
    public EnemyCat(String difficulty) {
        super.setImageId(R.drawable.catenemy);
        super.setHP(50);
        if (difficulty.equals("Easy")) {
            super.setDamage(5);
        } else if (difficulty.equals("Medium")) {
            super.setDamage(10);
        } else {
            super.setDamage(15);
        }
    }
} // EnemyCat
