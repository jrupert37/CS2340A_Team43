package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.cs2340a_team43.Interfaces.AttackObserver;
import com.example.cs2340a_team43.Interfaces.CollisionObserver;
import com.example.cs2340a_team43.Interfaces.ExecutableMovementPattern;
import com.example.cs2340a_team43.Models.Enemy;
import com.example.cs2340a_team43.Models.EnemyFactory;
import com.example.cs2340a_team43.Interfaces.Subject;
import com.example.cs2340a_team43.Interfaces.ViewObserver;
import java.util.ArrayList;
import java.util.List;

/*
 * This class acts as the View Model for each enemy. An instance of this
 * class interacts directly with the View and sends the View information from
 * the enemy model classes, in addition to altering the data within the enemy
 * models.
 * This class uses the Observer Design Pattern.
 */
public class EnemyViewModel extends CharacterViewModel implements Subject, AttackObserver,
        CollisionObserver {
    private final Enemy enemy;
    private final MapViewModel mapViewModel;
    private final List<CollisionObserver> collisionObservers;
    private final List<ViewObserver> viewObservers;
    private ExecutableMovementPattern enemyMovementPattern;

    public EnemyViewModel(Context cont, String type, MapViewModel mvm, int initialX, int initialY) {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy enemy = enemyFactory.makeEnemy(cont, type, initialX, initialY);
        this.enemy = enemy;
        super.setCharacter(enemy);
        this.enemyMovementPattern = enemyFactory.getMovementPattern(type, this);
        this.mapViewModel = mvm;
        collisionObservers = new ArrayList<>();
        viewObservers = new ArrayList<>();
    }

    public EnemyViewModel(String type, MapViewModel mvm, int enemyX, int enemyY) {
        EnemyFactory enemyFactory = new EnemyFactory();
        Enemy enemy = enemyFactory.makeEnemy(type, enemyX, enemyY);
        this.enemy = enemy;
        super.setCharacter(enemy);
        this.mapViewModel = mvm;
        collisionObservers = new ArrayList<>();
        viewObservers = new ArrayList<>();
    }
    public Bitmap getEnemySprite() {
        return this.enemy.getSprite();
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
        notifyWithPosition();
        notifyViewObservers();
    }

    public void moveEnemyRight() {
        if (willCollideWithWall(getEnemyX() + getEnemySpeed(), getEnemyY())) {
            return;
        }
        // otherwise...
        this.enemy.moveRight();
        notifyWithPosition();
        notifyViewObservers();
    }

    public void moveEnemyUp() {
        if (willCollideWithWall(getEnemyX(), getEnemyY() - getEnemySpeed())) {
            return;
        }
        // otherwise...
        this.enemy.moveUp();
        notifyWithPosition();
        notifyViewObservers();
    }

    public void moveEnemyDown() {
        if (willCollideWithWall(getEnemyX(), getEnemyY() + getEnemySpeed())) {
            return;
        }
        // otherwise...
        this.enemy.moveDown();
        notifyWithPosition();
        notifyViewObservers();
    }

    @Override
    public boolean updateWithAttack(int x, int y) {
        boolean attacked = (getEnemyX() == x && getEnemyY() == y);
        if (attacked) {
            this.gotAttacked();
        }
        return attacked;
    }

    @Override
    public void notifyWithPosition() {
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

    public void runMovementPattern() {
        this.enemyMovementPattern.start();
    }

    public void cancelMovement() {
        if (enemyMovementPattern != null) {
            this.enemyMovementPattern.stop();
        }
    }

    private void gotAttacked() {
        this.cancelMovement();
        this.enemy.setAttacked();
    }

    public boolean isAttacked() {
        return this.enemy.isAttacked();
    }
} // EnemyViewModel
