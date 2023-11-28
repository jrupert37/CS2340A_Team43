package com.example.cs2340a_team43.Models;

import android.content.Context;
import com.example.cs2340a_team43.R;

public class ScoreBoostPowerUp extends PowerUp {

    public ScoreBoostPowerUp(Context context, int x, int y) {
        super("score boost", context, R.drawable.increase, x, y);
    }

    public ScoreBoostPowerUp(int x, int y) {
        super("score boost", x, y);
    }
} // ScoreBoostPowerUp (PowerUp child class)
