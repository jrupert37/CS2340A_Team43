package com.example.cs2340a_team43.Models;

import android.content.Context;
import com.example.cs2340a_team43.R;

public class EnemyCat extends Enemy {
    public EnemyCat(Context context) {
        super.setSprite(R.drawable.catenemy, context);
    }

    public EnemyCat() {

    }
} // EnemyCat (Enemy child class)
