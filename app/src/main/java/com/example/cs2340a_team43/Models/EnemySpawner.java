package com.example.cs2340a_team43.Models;

import android.content.Context;
public abstract class EnemySpawner {

    public Enemy makeEnemy(Context context, String difficulty, String type) {
        Enemy enemy;
        enemy = createEnemy(difficulty, type);
        enemy.createBitmap(enemy.getImageId(), context);
        enemy.setInitialXY(10, 10);
        return enemy;
    }

    abstract Enemy createEnemy(String difficulty, String type);
}
