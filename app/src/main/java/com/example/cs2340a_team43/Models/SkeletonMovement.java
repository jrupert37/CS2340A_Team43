package com.example.cs2340a_team43.Models;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
public class SkeletonMovement extends Timer {
    private EnemyViewModel enemyViewModel;
    private Random rand = new Random();
    private int random;
    private Timer enemyTimer = new Timer();
    public SkeletonMovement(EnemyViewModel enemyViewModel) {
        this.enemyViewModel = enemyViewModel;
    }
    public void stop() {
        // cancel the Timer, stops enemy movement
        super.cancel();
    }
    public void run() {
        enemyTimer.schedule(new TimerTask() {
            @Override
                    public void run() {
                random = rand.nextInt(4);
                switch (random) {
                    case 0:
                        enemyViewModel.moveEnemyLeft();
                        break;
                    case 1:
                        enemyViewModel.moveEnemyRight();
                }
            }
        }, 500, 500);
        
    }
}
