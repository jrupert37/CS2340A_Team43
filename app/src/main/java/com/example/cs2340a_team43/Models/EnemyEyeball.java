package com.example.cs2340a_team43.Models;

import android.content.Context;
import com.example.cs2340a_team43.R;

public class EnemyEyeball extends Enemy {

    public EnemyEyeball(Context context) {
        super.setSprite(R.drawable.eyeball, context);
    }

    public EnemyEyeball() {

    }
} // EnemyEyeball (Enemy child class)
