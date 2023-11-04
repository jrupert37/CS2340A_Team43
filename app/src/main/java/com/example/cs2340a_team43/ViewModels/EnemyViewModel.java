package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import com.example.cs2340a_team43.Models.Enemy;
import com.example.cs2340a_team43.Models.EnemyFactory;
import com.example.cs2340a_team43.Models.EnemySpawner;
import com.example.cs2340a_team43.Models.MovementBehavior;
import com.example.cs2340a_team43.Models.Observer;
import com.example.cs2340a_team43.Models.Subject;

import java.util.ArrayList;
import java.util.List;

public class EnemyViewModel extends ViewModel implements Subject, Observer {
    private Enemy enemy;
    private MapViewModel mapViewModel;
    private int initialX;
    private int initialY;
    private List<Observer> observers;
    private boolean notified;
    EnemyFactory enemyFactory = new EnemyFactory();

    public EnemyViewModel(Context context, String difficulty, String type) {
        this.enemy = enemyFactory.makeEnemy(context, difficulty, type);
        observers = new ArrayList<>();
        notified = false;
    }
    public Bitmap getEnemyBitmap() {return this.enemy.getBitmap();}
    public int getEnemyX() {
        return this.enemy.getX();
    }

    public int getEnemyY() {
        return this.enemy.getY();
    }

    public int getEnemySpeed() {
        return this.enemy.getSpeed();
    }

    public void setInitialEnemyXY(int x, int y) {
        initialX = x;
        initialY = y;
        this.enemy.setInitialXY(initialX, initialY);
    }

    public void resetEnemyXY() {
        this.enemy.setInitialXY(initialX, initialY);
    }

    public void setMap(MapViewModel mapViewModel) {
        this.mapViewModel = mapViewModel;
    }

    public void moveEnemyLeft() {
        if (willCollideWithWall(getEnemyX() - 1, getEnemyY())) {
            return;
        }
        // otherwise...
        this.enemy.moveLeft();
        notifyObservers();
    }

    public void movePlayerRight() {
        if (willCollideWithWall(getEnemyX() + getEnemySpeed(), getEnemyY())) {
            return;
        }
        // otherwise...
        this.enemy.moveRight();
        notifyObservers();
    }

    public void movePlayerUp() {
        if (willCollideWithWall(getEnemyX(), getEnemyY() - getEnemySpeed())) {
            return;
        }
        // otherwise...
        this.enemy.moveUp();
        notifyObservers();
    }

    public void movePlayerDown() {
        if (willCollideWithWall(getEnemyX(), getEnemyY() + getEnemySpeed())) {
            return;
        }
        // otherwise...
        this.enemy.moveDown();
        notifyObservers();
    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        System.out.println("OVER HERE");
        this.notified = true;
        for (Observer o: observers) {
            o.update();
        }
        System.out.println("NOTIFIED #1: " + notified);
    }

    public boolean isNotified() {
        return notified;
    }

    public void update() {}


    public boolean willCollideWithWall(int newX, int newY) {
        return mapViewModel.isAWall(newX, newY);
    }

    public boolean playerIsAtExit() {
        return mapViewModel.xyIsAnExit(getEnemyX(), getEnemyY());
    }

    public int getEnemyHP() {
        return this.enemy.getHp();
    }

    public void setEnemyMovementBehavior(MovementBehavior behavior) {
        this.enemy.setMovementBehavior(behavior);
    }
}
