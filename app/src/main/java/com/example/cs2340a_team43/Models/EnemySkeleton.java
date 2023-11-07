package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.R;

public class EnemySkeleton extends Enemy {
    public EnemySkeleton(String difficulty) {
        super.setImageId(R.drawable.skeletonenemy);
        super.setHP(100);
        if (difficulty.equals("Easy")) {
            super.setDamage(1);
        } else if (difficulty.equals("Medium")) {
            super.setDamage(3);
        } else {
            super.setDamage(5);
        }
    }
} // EnemySkeleton
