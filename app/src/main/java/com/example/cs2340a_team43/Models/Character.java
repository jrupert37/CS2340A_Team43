package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.cs2340a_team43.Interfaces.MovementBehavior;
import com.example.cs2340a_team43.Interfaces.MovementBehavior.MovementDirection;

/*
* This is a parent class for any Character type, including player and enemies
*/
public abstract class Character {
    private MovementBehavior movementBehavior;
    protected XYPair xyCoordinates;
    private Bitmap leftFacing;
    private Bitmap rightFacing;
    protected Bitmap bitmap;

    protected Character() {
        this.movementBehavior = new WalkMovement();   // set to WalkMovement by default
        this.xyCoordinates = new XYPair(0, 0);  // set to (0,0) by default
    }

    public int getX() {
        return this.xyCoordinates.x();
    }

    public  int getY() {
        return this.xyCoordinates.y();
    }

    public void setInitialXY(int x, int y) {
        this.xyCoordinates.setXY(x, y);
    }


    public Bitmap getSprite() {
        return this.bitmap;
    }

    public void setSprite(int imageId, Context context) {
        this.rightFacing = BitmapFactory.decodeResource(context.getResources(), imageId);
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        this.leftFacing = Bitmap.createBitmap(rightFacing, 0, 0,
                rightFacing.getWidth(), rightFacing.getHeight(), matrix, false);
        this.bitmap = rightFacing;
    }

    public void moveLeft() {
        faceLeft();
        updatePosition(MovementDirection.LEFT);
    }

    public void moveRight() {
        faceRight();
        updatePosition(MovementDirection.RIGHT);
    }

    public void moveUp() {
        updatePosition(MovementDirection.UP);
    }

    public void moveDown() {
        updatePosition(MovementDirection.DOWN);
    }

    private void faceRight() {
        this.bitmap = rightFacing;
    }

    private void faceLeft() {
        this.bitmap = leftFacing;
    }

    protected void updatePosition(MovementDirection direction) {
        this.performMove(direction);
    }

    public void performMove(MovementDirection dir) {
        movementBehavior.move(xyCoordinates, dir);
    }

    public void setMovementBehavior(MovementBehavior behavior) {
        this.movementBehavior = behavior;
    }

    public int getSpeed() {
        return this.movementBehavior.getSpeed();
    }

} // Character (abstract parent)
