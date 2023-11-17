package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.Interfaces.IPowerUp;

public class HealthDecorator extends PowerUpDecorator {

    public HealthDecorator(IPowerUp powerUp) {
        super.powerUp = powerUp;
        super.numPowerUps = powerUp.numPowerUps() + 1;

    }

    @Override
    public String listPowerUps() {
        if (super.numPowerUps == 1) {
            return super.listPowerUps() + " Increased Health";
        }
        return super.powerUp.listPowerUps() + " + Increased Health";
    }
}
