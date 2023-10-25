package com.example.cs2340a_team43.Models;

public class EnemyOneFactory extends EnemySpawner {
    protected Enemy createEnemy(String difficulty) {
        return new EnemyOne(difficulty);
    }
}
