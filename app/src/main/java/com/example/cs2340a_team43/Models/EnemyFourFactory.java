package com.example.cs2340a_team43.Models;

public class EnemyFourFactory extends EnemySpawner {
    protected Enemy createEnemy() {
        return new EnemyFour();
    }
}
