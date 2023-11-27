package com.example.cs2340a_team43.Models;

import android.content.Context;
import com.example.cs2340a_team43.R;

public class EnemySkeleton extends Enemy {
    public EnemySkeleton(Context context) {
        super.setSprite(R.drawable.skeletonenemy, context);
    }

    public EnemySkeleton() {

    }
} // EnemySkeleton (Enemy child class)
