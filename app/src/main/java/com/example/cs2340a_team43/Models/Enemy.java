package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.cs2340a_team43.R;
import androidx.core.util.Pair;
import com.example.cs2340a_team43.Models.MovementBehavior.MovementDirection;


public abstract class Enemy extends Character {
    Context context;
    int hp;
    int imageId;
    Bitmap bitmap;
    int damage;
    ControllableMovement movementPattern;

    public int getHP() {
        return hp;
    }

    public int getX() {
        // the "first" element of the xyCoordinates pair is the x coordinate
        return xyCoordinates.first;
    }

    public int getY() {
        // the "second" element of the xyCoordinates pair is the y coordinate
        return xyCoordinates.second;
    }

    public void setInitialXY(int x, int y) {
        xyCoordinates = new Pair<Integer, Integer>(x, y);
    }

    public void createBitmap(int imageId, Context context) {
        this.imageId = imageId;
        this.context = context;
        this.bitmap = BitmapFactory.decodeResource(this.context.getResources(), imageId);
    }

    public void moveLeft() {
        updatePosition(MovementBehavior.MovementDirection.LEFT);
    }

    public void moveRight() {
        updatePosition(MovementBehavior.MovementDirection.RIGHT);
    }

    public void moveUp() {
        updatePosition(MovementBehavior.MovementDirection.UP);
    }

    public void moveDown() {
        updatePosition(MovementBehavior.MovementDirection.DOWN);
    }
    private void updatePosition(MovementDirection direction) {
        this.xyCoordinates = performMove(xyCoordinates, direction);
        System.out.println("X: " + xyCoordinates.first);
        System.out.println("Y: " + xyCoordinates.second);
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }
    public int getDamage() {
        return this.damage;
    }

    public int getHp() {
        return this.hp;
    }
    public int getImageId() {
        return this.imageId;
    }
    public void runMovementPattern() {
        this.movementPattern.start();
    }
    public void cancelMovement() {
        this.movementPattern.stop();
    }
}
