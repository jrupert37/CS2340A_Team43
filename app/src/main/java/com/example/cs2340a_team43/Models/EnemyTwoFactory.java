package com.example.cs2340a_team43.Models;

public class EnemyTwoFactory extends EnemySpawner {
    protected Enemy createEnemy(String difficulty) {
        return new EnemyTwo(difficulty);
    }
}
