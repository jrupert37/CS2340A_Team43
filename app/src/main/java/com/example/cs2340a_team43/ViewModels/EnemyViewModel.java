package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.lifecycle.ViewModel;
import com.example.cs2340a_team43.Models.ExecutableMovementPattern;
import com.example.cs2340a_team43.Models.Enemy;
import com.example.cs2340a_team43.Models.EnemyFactory;
import com.example.cs2340a_team43.Models.MovementBehavior;
import com.example.cs2340a_team43.Models.Observer;
import com.example.cs2340a_team43.Models.Subject;
import java.util.ArrayList;
import java.util.List;

/*
 * This class acts as the View Model for each enemy. An instance of this
 * class interacts directly with the View and sends the View information from
 * the enemy model classes, in addition to altering the data within the enemy
 * models.
 * This class uses the Observer Design Pattern.
 */
public class EnemyViewModel extends ViewModel implements Subject, Observer {
    private final Enemy enemy;
    private final MapViewModel mapViewModel;
    private final List<Observer> observers;
    private boolean notified;
    private final ExecutableMovementPattern enemyMovementPattern;

    public EnemyViewModel(Context context, String difficulty, String type, MapViewModel mvm,
                          int enemyX, int enemyY) {
        EnemyFactory enemyFactory = new EnemyFactory();
        this.enemy = enemyFactory.makeEnemy(context, difficulty, type, enemyX, enemyY);
        this.enemyMovementPattern = enemyFactory.getMovementPattern(type, this);
        this.mapViewModel = mvm;
        observers = new ArrayList<>();
        notified = false;
    }

    public Bitmap getEnemyBitmap() {
        return this.enemy.getBitmap();
    }

    public int getEnemyX() {
        return this.enemy.getX();
    }

    public int getEnemyY() {
        return this.enemy.getY();
    }

    public int getEnemySpeed() {
        return this.enemy.getSpeed();
    }

    public void moveEnemyLeft() {
        if (willCollideWithWall(getEnemyX() - getEnemySpeed(), getEnemyY())) {
            return;
        }
        // otherwise...
        this.enemy.moveLeft();
        notifyObservers();
    }

    public void moveEnemyRight() {
        if (willCollideWithWall(getEnemyX() + getEnemySpeed(), getEnemyY())) {
            return;
        }
        // otherwise...
        this.enemy.moveRight();
        notifyObservers();
    }

    public void moveEnemyUp() {
        if (willCollideWithWall(getEnemyX(), getEnemyY() - getEnemySpeed())) {
            return;
        }
        // otherwise...
        this.enemy.moveUp();
        notifyObservers();
    }

    public void moveEnemyDown() {
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
            o.update(getEnemyX(), getEnemyY());
        }
        System.out.println("NOTIFIED #1: " + notified);
    }

    @Override
    public void update(int x, int y) {

    }

    public boolean willCollideWithWall(int newX, int newY) {
        return mapViewModel.isAWall(newX, newY);
    }

    public int getEnemyHP() {
        return this.enemy.getHp();
    }

    public void setEnemyMovementBehavior(MovementBehavior behavior) {
        this.enemy.setMovementBehavior(behavior);
    }

    public void runMovementPattern() {
        this.enemyMovementPattern.start();
    }

    public void cancelMovement() {
        this.enemyMovementPattern.stop();
    }
} // EnemyViewModel
