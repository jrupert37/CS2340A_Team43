package com.example.cs2340a_team43.Models;

import android.content.Context;
public abstract class EnemySpawner {

    public Enemy makeEnemy(Context context, String difficulty) {
        Enemy enemy;
        enemy = createEnemy(difficulty);
        enemy.createBitmap(context);
        enemy.setInitialXY(10, 10);
        return enemy;
    }

    abstract Enemy createEnemy(String difficulty);
}
