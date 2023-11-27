package com.example.cs2340a_team43.Models;

import android.content.Context;
import com.example.cs2340a_team43.R;

public class WallWalkerPowerUp extends PowerUp {
    public WallWalkerPowerUp(Context context, int x, int y) {
        super("wall walker", context, R.drawable.boots, x, y);
    }

    public WallWalkerPowerUp(int x, int y) {
        super("wall walker", x, y);
    }
} // WallWalkerPowerUp (PowerUp child class)
