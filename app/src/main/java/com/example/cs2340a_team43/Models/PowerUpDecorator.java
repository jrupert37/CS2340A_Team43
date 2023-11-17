package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.Interfaces.IPowerUp;

public class PowerUpDecorator implements IPowerUp {
    protected IPowerUp powerUp;
    private String description = "Power-Ups:";
    protected int numPowerUps = 0;

    @Override
    public String listPowerUps() {
        return description;
    }

    public int numPowerUps() {
        return numPowerUps;
    }
}
