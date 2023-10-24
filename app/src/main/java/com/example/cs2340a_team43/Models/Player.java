package com.example.cs2340a_team43.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.util.Pair;

import com.example.cs2340a_team43.Models.MovementBehavior.MovementDirection;


public class Player extends Character {
    private static Player player;
    private String playerName;
    private Context context;
    private int hp;
    private int imageId;
    private Bitmap bitmap;

    private Player() {
        // null constructor, player attributes will be concretely initialized later
        this.hp = 0;
        //xyCoordinates = new Pair<>(0, 0);
        this.imageId = 0;
        this.bitmap = null;
        this.movementBehavior = new WalkMovement();
    }
    public Player(MovementBehavior run) {
        // temp constructor, player attributes will be concretely initialized later
        this.hp = 0;
        xyCoordinates = new Pair<>(0, 0);
        this.imageId = 0;
        this.bitmap = null;
        this.movementBehavior = new RunMovement();
    }
    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }
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

    public void setImageId(int imageId, Context context) {
        this.imageId = imageId;
        this.context = context;
        this.bitmap = BitmapFactory.decodeResource(this.context.getResources(), imageId);
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
        this.xyCoordinates = performMove(xyCoordinates, direction);
        System.out.println("X: " + xyCoordinates.first);
        System.out.println("Y: " + xyCoordinates.second);
    }
    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public int getHp() {
        return this.hp;
    }
} // Player
