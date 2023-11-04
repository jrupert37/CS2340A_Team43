package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.ViewModels.EnemyViewModel;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class CatMovementPattern extends Timer implements ControllableMovement {

    private final EnemyViewModel evm;
    private int nextDirectionIndex = 0;
    private final String[] directions = new String[] {
        "right", "right", "down", "down", "left", "left", "up", "up"
    };
    private final long moveTime;
    public CatMovementPattern(EnemyViewModel evm) {
        this.evm = evm;
        Random rand = new Random();
        moveTime = rand.nextInt(500) + 500;
    }

    public void stop() {
        cancel();
    }

    public void start() {
        schedule(new TimerTask() {
            @Override
            public void run() {
                String nextDirection = directions[nextDirectionIndex];
                switch (nextDirection) {
                    case "right":
                        evm.moveEnemyRight();
                        break;
                    case "down":
                        evm.moveEnemyDown();
                        break;
                    case "left":
                        evm.moveEnemyLeft();
                        break;
                    case "up":
                        evm.moveEnemyUp();
                        break;
                    default:
                        break;
                }
                nextDirectionIndex = (nextDirectionIndex + 1) % directions.length;
            }
        }, moveTime, moveTime);
    }
}
