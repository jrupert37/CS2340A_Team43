package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.Interfaces.IPowerUp;

public class ScoreBoostDecorator extends PowerUpDecorator {
    public ScoreBoostDecorator(IPowerUp powerUp) {
        super.powerUp = powerUp;
        super.numPowerUps = powerUp.numPowerUps() + 1;
    }

    public String listPowerUps() {
        if (super.numPowerUps == 1) {
            return super.listPowerUps() + " Score Boost";
        }
        return super.powerUp.listPowerUps() + " + Score Boost";
    }
}
