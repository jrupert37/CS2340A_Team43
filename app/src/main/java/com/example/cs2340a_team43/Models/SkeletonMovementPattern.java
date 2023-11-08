package com.example.cs2340a_team43.Models;
import com.example.cs2340a_team43.ViewModels.EnemyViewModel;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import com.example.cs2340a_team43.Models.MovementBehavior.MovementDirection;
public class SkeletonMovementPattern extends Timer implements ExecutableMovementPattern {
    private EnemyViewModel enemyViewModel;
    private Timer enemyTimer = new Timer();
    private String direction;
    public SkeletonMovementPattern(EnemyViewModel enemyViewModel) {
        this.enemyViewModel = enemyViewModel;
        this.direction = "left";
    }

    @Override
    public void stop() {
        // cancel the Timer, stops enemy movement
        super.cancel();
    }

    @Override
    public void start() {
        super.schedule(new TimerTask() {
            @Override
            public void run() {
                int x = enemyViewModel.getEnemyX();
                int y = enemyViewModel.getEnemyY();
                switch (direction) {
                case "left":
                    if (enemyViewModel.willCollideWithWall(x - 1, y)) {
                        direction = "right";
                        enemyViewModel.moveEnemyRight();
                    } else {
                        enemyViewModel.moveEnemyLeft();
                    }
                    break;
                case "right":
                    if (enemyViewModel.willCollideWithWall(x + 1, y)) {
                        direction = "left";
                        enemyViewModel.moveEnemyLeft();
                    } else {
                        enemyViewModel.moveEnemyRight();
                    }
                    break;
                default:
                    break;
                }
            }
        }, 500, 500);
        
    }
}
