package com.example.cs2340a_team43.Models;

import android.content.Context;

/*
 * This abstract parent class is responsible for initializing
 * different types of enemies for an Enemy View Model.
 * Initialize a certain enemy based on the given "type" and "difficulty",
 * with the given (x,y) starting location.
 */
public abstract class EnemySpawner {
    public Enemy makeEnemy(Context context, String difficulty, String type, int x, int y) {
        Enemy enemy;
        enemy = createEnemy(difficulty, type);
        enemy.createBitmap(enemy.getImageId(), context);
        enemy.setInitialXY(x, y);
        enemy.setMovementBehavior(new WalkMovement());
        return enemy;
    }

    abstract Enemy createEnemy(String difficulty, String type);
} // EnemySpawner (abstract parent)
