package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.ViewModels.EnemyViewModel;

/*
 * This class uses the EnemySpawn abstract parent class.
 * This class is responsible for returning the correct kind of
 * enemy and enemy movement pattern based on the given "type".
 * This class is useful for implementing the Factory Design Pattern.
 */
public class EnemyFactory extends EnemySpawner {
    protected Enemy createEnemy(String difficulty, String type) {
        switch (type) {
        case "cat":
            return new EnemyCat(difficulty);
        case "grimreaper":
            return new EnemyGrimReaper(difficulty);
        case "skeleton":
            return new EnemySkeleton(difficulty);
        case "eyeball":
            return new EnemyEyeball(difficulty);
        default:
            return null;
        }
    }

    public ControllableMovementPattern getMovementPattern(String type, EnemyViewModel evm) {
        if (type.equals("cat")) {
            return new CatMovementPattern(evm);
        } else if (type.equals("eyeball")) {
            return new EyeballMovementPattern(evm);
        } else {
            return null;
        }
    }
} // EnemyFactory
