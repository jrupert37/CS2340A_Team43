package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.ViewModels.EnemyViewModel;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GrimreaperMovement extends Timer {
    private EnemyViewModel enemyViewModel;
    private Random rand = new Random();
    private int random;
    private Timer enemyTimer = new Timer();

    public GrimreaperMovement (EnemyViewModel enemyViewModel) {
        this.enemyViewModel = enemyViewModel;
    }
    public void move(){
        enemyTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                random = rand.nextInt(4);
                switch (random) {
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
                }
            }
        }, 500, 500);
    }
}
