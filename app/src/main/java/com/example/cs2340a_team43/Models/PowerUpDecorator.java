package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.Interfaces.PowerUp;

public class PowerUpDecorator implements PowerUp {
    public PowerUp powerUp;
    String listPowerUps;
    public PowerUpDecorator() {
        listPowerUps = "";
    }
    public String listPowerUps(){
        return this.listPowerUps;
    }
    public void addEffect(){
    }
}
