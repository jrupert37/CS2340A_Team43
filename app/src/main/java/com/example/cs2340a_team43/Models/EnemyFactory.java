package com.example.cs2340a_team43.Models;

import android.content.Context;
import com.example.cs2340a_team43.EnemyMovementPatterns.CatMovementPattern;
import com.example.cs2340a_team43.EnemyMovementPatterns.EyeballMovementPattern;
import com.example.cs2340a_team43.EnemyMovementPatterns.GrimreaperMovementPattern;
import com.example.cs2340a_team43.EnemyMovementPatterns.SkeletonMovementPattern;
import com.example.cs2340a_team43.Interfaces.ExecutableMovementPattern;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;

/*
 * This class uses the EnemySpawn abstract parent class.
 * This class is responsible for returning the correct kind of
 * enemy and enemy movement pattern based on the given "type".
 * This class is useful for implementing the Factory Design Pattern.
 */
public class EnemyFactory extends EnemySpawner {
    protected Enemy createEnemy(String type, Context context) {
        switch (type) {
        case "cat":
            return new EnemyCat(context);
        case "grimreaper":
            return new EnemyGrimReaper(context);
        case "skeleton":
            return new EnemySkeleton(context);
        case "eyeball":
            return new EnemyEyeball(context);
        default:
            return null;
        }
    }

    @Override
    protected Enemy createEnemy(String type) {
        switch (type) {
        case "cat":
            return new EnemyCat();
        case "grimreaper":
            return new EnemyGrimReaper();
        case "skeleton":
            return new EnemySkeleton();
        case "eyeball":
            return new EnemyEyeball();
        default:
            return null;
        }
    }

    public ExecutableMovementPattern getMovementPattern(String type, EnemyViewModel evm) {
        switch (type) {
        case "cat":
            return new CatMovementPattern(evm);
        case "eyeball":
            return new EyeballMovementPattern(evm);
        case "grimreaper":
            return new GrimreaperMovementPattern(evm);
        case "skeleton":
            return new SkeletonMovementPattern(evm);
        default:
            return null;
        }
    }
} // EnemyFactory (EnemySpawner child class)
