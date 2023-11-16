package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.Interfaces.PowerUp;

public class ScoreBoostDecorator extends PowerUpDecorator{
    ScoreBoostDecorator(PowerUp powerUp){
        this.powerUp = powerUp;
    }
    public String listPowerUps(){
        return this.powerUp.listPowerUps() + "scoreboost";
    }
}
