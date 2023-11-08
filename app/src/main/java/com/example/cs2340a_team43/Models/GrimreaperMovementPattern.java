package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.ViewModels.EnemyViewModel;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GrimreaperMovementPattern extends Timer implements ExecutableMovementPattern {
    private EnemyViewModel enemyViewModel;
    private Random rand = new Random();
    private int random;

    public GrimreaperMovementPattern (EnemyViewModel enemyViewModel) {
        this.enemyViewModel = enemyViewModel;
    }

    @Override
    public void stop() {
        // cancel the Timer, stops enemy movement
        super.cancel();
    }

    @Override
    public void start() {
        Random rand = new Random();
        super.schedule(new TimerTask() {
            @Override
            public void run() {
                int randomValue = rand.nextInt(4);
                switch (randomValue) {
                    case 0:
                        enemyViewModel.moveEnemyDown();
                        break;
                    case 1:
                        enemyViewModel.moveEnemyUp();
                        break;
                    case 2:
                        enemyViewModel.moveEnemyRight();
                        break;
                    case 3:
                        enemyViewModel.moveEnemyLeft();
                        break;
                    default:
                        break;
                }
            }
        }, 500, 500);
    }
}
