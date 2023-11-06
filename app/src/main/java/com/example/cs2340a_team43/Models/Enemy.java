package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.cs2340a_team43.Models.MovementBehavior.MovementDirection;

/*
* This is a parent class for all Enemy types. This class extends the Character parent class.
*/
public abstract class Enemy extends Character {
    private int hp;
    private int imageId;
    private Bitmap bitmap;
    private int damage;

    protected Enemy() {
        super();
    }

    public int getHP() {
        return hp;
    }

    public void createBitmap(int imageId, Context context) {
        this.imageId = imageId;
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), imageId);
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
        super.performMove(direction);
        System.out.println("X: " + super.getX());
        System.out.println("Y: " + super.getY());
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

    protected void setImageId(int imageId) {
        this.imageId = imageId;
    }

    protected void setDamage(int damage) {
        this.damage = damage;
    }

    protected void setHP(int hp) {
        this.hp = hp;
    }
} // Enemy (abstract parent)
