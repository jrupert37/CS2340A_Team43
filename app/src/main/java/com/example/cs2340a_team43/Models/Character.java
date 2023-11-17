package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.util.Pair;
import com.example.cs2340a_team43.Interfaces.MovementBehavior;
import com.example.cs2340a_team43.Interfaces.MovementBehavior.MovementDirection;

/*
* This is a parent class for any Character type, including player and enemies
*/
public abstract class Character {
    private MovementBehavior movementBehavior;
    protected Pair<Integer, Integer> xyCoordinates;
    protected Bitmap bitmap;
    protected int hp;
    protected int damageTaken;

    protected Character() {
        this.movementBehavior = new WalkMovement(); // set to WalkMovement by default
        this.xyCoordinates = new Pair<>(0, 0);      // set to (0,0) by default
        this.damageTaken = 0;
    }

    public int getX() {
        return this.xyCoordinates.first;
    }

    public  int getY() {
        return this.xyCoordinates.second;
    }

    public void setInitialXY(int x, int y) {
        this.xyCoordinates = new Pair<>(x, y);
    }

    public void setInitialHP(String difficulty) {
        if (difficulty.equals("Easy")) {
            hp = 50;
            damageTaken = 2;
        } else if (difficulty.equals("Medium")) {
            hp = 30;
            damageTaken = 3;
        } else {
            hp = 15;
            damageTaken = 5;
        }
    }

    public Bitmap getSprite() {
        return this.bitmap;
    }

    public void setSprite(int imageId, Context context) {
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), imageId);
    }

    public void moveLeft() {
        updatePosition(MovementDirection.LEFT);
    }

    public void moveRight() {
        updatePosition(MovementDirection.RIGHT);
    }

    public void moveUp() {
        updatePosition(MovementDirection.UP);
    }

    public void moveDown() {
        updatePosition(MovementDirection.DOWN);
    }

    protected void updatePosition(MovementDirection direction) {
        this.performMove(direction);
    }

    public void performMove(MovementDirection dir) {
        this.setXYCoordinates(movementBehavior.move(this.xyCoordinates, dir));
    }

    public int getHP() {
        return this.hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void setMovementBehavior(MovementBehavior behavior) {
        this.movementBehavior = behavior;
    }

    public int getSpeed() {
        return this.movementBehavior.getSpeed();
    }

    protected int getDamageTaken() {
        return this.damageTaken;
    }

    public void gotHit() {
        this.setHP(getHP() - getDamageTaken());
    }

    private void setXYCoordinates(Pair<Integer, Integer> xyCoordinates) {
        this.xyCoordinates = xyCoordinates;
    }
} // Character (abstract parent)
