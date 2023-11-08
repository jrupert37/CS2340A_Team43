package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.util.Pair;
import com.example.cs2340a_team43.Models.MovementBehavior.MovementDirection;

/*
 * This class serves as the player "model" and holds all necessary information,
 * including name, health (hp), and a representative image/bitmap.
 * This class is used by the PlayerViewModel to handle interactions with the
 * Game View.
 * Player movement uses Character (parent) class's performMove function with a
 * specified direction.
 * This class follows the Singleton Design Pattern.
 */
public class Player extends Character {
    private static Player player;
    private String playerName;
    private int hp;
    private Bitmap bitmap;
    private Pair<Integer, Integer> previousXY;
    // super.xyCoordinates (contained in Character parent class

    /* Private constructor to prevent other classes from instantiating a new player */
    private Player() {
        super(); // calls the Character constructor
        // player attributes will be more concretely initialized later
        previousXY = new Pair<>(1, 1);
        this.hp = 0;
        this.bitmap = null;
    }

    //    public Player(MovementBehavior run) {
    //        // temp constructor, player attributes will be concretely initialized later
    //        this.hp = 0;
    //        xyCoordinates = new Pair<>(0, 0);
    //        this.imageId = 0;
    //        this.bitmap = null;
    //        //this.movementBehavior = new RunMovement();
    //    }

    /* Public static getter method, so every class can access the single player instance */
    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public int getHP() {
        return hp;
    }

    public void setHP(String difficulty) {
        if (difficulty.equals("Easy")) {
            hp = 50;
        } else if (difficulty.equals("Medium")) {
            hp = 30;
        } else {
            hp = 15;
        }
    }

    public String getName() {
        return playerName;
    }

    public void setName(String playerName) {
        this.playerName = playerName;
        if (playerName == null || playerName == "") {
            throw new IllegalStateException();
        }
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

    private void updatePosition(MovementDirection direction) {
        setPreviousXY(super.getX(), super.getY());
        super.performMove(direction);
        System.out.println("X: " + super.getX());
        System.out.println("Y: " + super.getY());
    }

    private void setPreviousXY(int x, int y) {
        this.previousXY = new Pair<>(x, y);
    }

    public int getPreviousX() {
        return this.previousXY.first;
    }

    public int getPreviousY() {
        return this.previousXY.second;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public int getHp() {
        return this.hp;
    }
} // Player
