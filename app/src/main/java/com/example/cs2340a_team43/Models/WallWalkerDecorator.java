package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.Interfaces.IPowerUp;

public class WallWalkerDecorator extends PowerUpDecorator {
    public WallWalkerDecorator(IPowerUp powerUp) {
        super.powerUp = powerUp;
        super.numPowerUps = powerUp.numPowerUps() + 1;
        super.currentType = "wall walker";
    }
    public String listPowerUps() {
        if (super.numPowerUps == 1) {
            return super.listPowerUps() + " Wall Walker";
        }
        return super.powerUp.listPowerUps() + " + Wall Walker";
    }
} // WallWalker (PowerUpDecorator child class)

