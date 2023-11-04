package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.ViewModels.EnemyViewModel;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class EyeballMovement extends Thread {

    //private Enemy enemy;
    private EnemyViewModel evm;

    public EyeballMovement (EnemyViewModel evm) {
        this.evm = evm;
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Random rand = new Random();
                int randomValue = rand.nextInt(4);
                switch (randomValue) {
                    case 0:
                        evm.moveEnemyDown();
                        break;
                    case 1:
                        evm.moveEnemyUp();
                        break;
                    case 2:
                        evm.moveEnemyRight();
                        break;
                    case 3:
                        evm.moveEnemyLeft();
                        break;
                }
            }
        }, 2000, 2000); // delay 1sec, then execute run() every 1sec til score is 0
    }


}
