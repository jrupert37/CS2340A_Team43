package com.example.cs2340a_team43.Models;

public class BaseDecorator extends PowerUpDecorator {
    private String powerUpDescription;

    public BaseDecorator() {
        this.powerUpDescription = "Achievements: Started Game";
    }

    @Override
    public String listPowerUps() {
        return powerUpDescription;
    }
}
