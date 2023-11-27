package com.example.cs2340a_team43.Models;

import android.content.Context;
import com.example.cs2340a_team43.R;

public class HealthPowerUp extends PowerUp {
    public HealthPowerUp(Context context, int x, int y) {
        super("health", context, R.drawable.health, x, y);
    }
} // HealthPowerUp (PowerUp child class)
