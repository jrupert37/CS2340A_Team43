package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemyGrimReaper extends Enemy {
    public EnemyGrimReaper(String difficulty) {
        super.setImageId(R.drawable.grimreaper);
        super.setHP(50);
        if (difficulty.equals("Easy")) {
            super.setDamage(10);
        } else if (difficulty.equals("Medium")) {
            super.setDamage(15);
        } else {
            super.setDamage(20);
        }
    }
} // EnemyGrimReaper
