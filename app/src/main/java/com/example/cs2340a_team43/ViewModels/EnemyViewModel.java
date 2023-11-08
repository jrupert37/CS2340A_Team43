package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.cs2340a_team43.Models.Character;
import com.example.cs2340a_team43.Models.CollisionObserver;
import com.example.cs2340a_team43.Models.ExecutableMovementPattern;
import com.example.cs2340a_team43.Models.Enemy;
import com.example.cs2340a_team43.Models.EnemyFactory;
import com.example.cs2340a_team43.Models.MovementBehavior;
import com.example.cs2340a_team43.Models.Subject;
import com.example.cs2340a_team43.Models.ViewObserver;

import java.util.ArrayList;
import java.util.List;

/*
 * This class acts as the View Model for each enemy. An instance of this
 * class interacts directly with the View and sends the View information from
 * the enemy model classes, in addition to altering the data within the enemy
 * models.
 * This class uses the Observer Design Pattern.
 */
public class EnemyViewModel extends CharacterViewModel implements Subject, CollisionObserver {
    private final Enemy enemy;
    private final MapViewModel mapViewModel;
    private final List<CollisionObserver> collisionObservers;
    private final List<ViewObserver> viewObservers;
    private boolean notified;
    private final ExecutableMovementPattern enemyMovementPattern;

    public EnemyViewModel(Context context, String difficulty, String type, MapViewModel mvm,
                          int enemyX, int enemyY) {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy enemy = enemyFactory.makeEnemy(context, difficulty, type, enemyX, enemyY);
        this.enemy = enemy;
        super.setCharacter(enemy);
        this.enemyMovementPattern = enemyFactory.getMovementPattern(type, this);
        this.mapViewModel = mvm;
        collisionObservers = new ArrayList<>();
        viewObservers = new ArrayList<>();
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
        notifyOfPosition();
        notifyViewObservers();
    }

    public void moveEnemyRight() {
        if (willCollideWithWall(getEnemyX() + getEnemySpeed(), getEnemyY())) {
            return;
        }
        // otherwise...
        this.enemy.moveRight();
        notifyOfPosition();
        notifyViewObservers();
    }

    public void moveEnemyUp() {
        if (willCollideWithWall(getEnemyX(), getEnemyY() - getEnemySpeed())) {
            return;
        }
        // otherwise...
        this.enemy.moveUp();
        notifyOfPosition();
        notifyViewObservers();
    }

    public void moveEnemyDown() {
        if (willCollideWithWall(getEnemyX(), getEnemyY() + getEnemySpeed())) {
            return;
        }
        // otherwise...
        this.enemy.moveDown();
        notifyOfPosition();
        notifyViewObservers();
    }

    @Override
    public void notifyOfPosition() {
        for (CollisionObserver co : collisionObservers) {
            co.updateWithPosition(getEnemyX(), getEnemyY());
        }
    }

    @Override
    public void notifyViewObservers() {
        for (ViewObserver vo : viewObservers) {
            vo.update();
        }
    }

    @Override
    public void addCollisionObserver(CollisionObserver co) {
        this.collisionObservers.add(co);
    }

    @Override
    public void addViewObserver(ViewObserver vo) {
        this.viewObservers.add(vo);
    }

    @Override
    public void removeCollisionObserver(CollisionObserver co) {
        this.collisionObservers.remove(co);
    }

    @Override
    public void removeViewObserver(ViewObserver vo) {
        this.viewObservers.remove(vo);
    }

    @Override
    public boolean updateWithPosition(int x, int y) {
        return (getEnemyX() == x && getEnemyY() == y);
    }

    public boolean willCollideWithWall(int newX, int newY) {
        return mapViewModel.isAWall(newX, newY);
    }

    public int getEnemyHP() {
        return this.enemy.getHp();
    }

    public int getEnemyDamage() {
        return this.enemy.getDamage();
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
