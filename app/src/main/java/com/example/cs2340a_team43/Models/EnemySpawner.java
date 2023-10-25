package com.example.cs2340a_team43.Models;

import android.content.Context;
public abstract class EnemySpawner {

    public Enemy makeEnemy(Context context) {
        Enemy enemy;
        enemy = createEnemy();
        enemy.createBitmap(context);
        enemy.setInitialXY(10, 10);
        return enemy;
    }

    abstract Enemy createEnemy();
}
