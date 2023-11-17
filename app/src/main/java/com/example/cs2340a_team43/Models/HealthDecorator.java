package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.Interfaces.PowerUp;

public class HealthDecorator extends PowerUpDecorator{
    public HealthDecorator(PowerUp powerUp){
        this.powerUp = powerUp;
    }
    public String listPowerUps(){
        return this.powerUp.listPowerUps() + "health";
    }
    public void addEffect(){
        this.player + player.setHP(player.getHP()+ 50);
    }
}
