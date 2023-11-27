package com.example.cs2340a_team43.Models;

import android.content.Context;
import com.example.cs2340a_team43.R;

public class EnemyGrimReaper extends Enemy {
    public EnemyGrimReaper(Context context) {
        super.setSprite(R.drawable.grimreaper, context);
    }

    public EnemyGrimReaper() {

    }
} // EnemyGrimReaper (Enemy child class)
