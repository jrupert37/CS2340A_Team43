package com.example.cs2340a_team43.Models;

import androidx.core.util.Pair;
import com.example.cs2340a_team43.Interfaces.MovementBehavior.MovementDirection;
import com.example.cs2340a_team43.Interfaces.IPowerUp;

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
    private Pair<Integer, Integer> previousXY;
    private IPowerUp powerUps;
    private boolean scoreBoost;
    private boolean wallWalker;
    // super.xyCoordinates (contained in Character parent class

    /* Private constructor to prevent other classes from instantiating a new player */
    private Player() {
        super(); // calls the Character constructor
        // player attributes will be more concretely initialized later
        previousXY = new Pair<>(1, 1);
        this.hp = 0;
        this.bitmap = null;
        this.damageTaken = 0;
        resetPowerUps();
    }

    public void resetPowerUps() {
        powerUps = new PowerUpDecorator();
        scoreBoost = false;
        wallWalker = false;
    }

    /* Public static getter method, so every class can access the single player instance */
    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public String getName() {
        return playerName;
    }

    public void setName(String playerName) {
        this.playerName = playerName;
        if (playerName == null || playerName.equals("")) {
            throw new IllegalStateException();
        }
    }

    public int getPreviousX() {
        return this.previousXY.first;
    }

    public int getPreviousY() {
        return this.previousXY.second;
    }

    private void setPreviousXY(int x, int y) {
        this.previousXY = new Pair<>(x, y);
    }

    private void recoilXY() {
        int tempX = super.getX();
        int tempY = super.getY();
        super.xyCoordinates = new Pair<>(getPreviousX(), getPreviousY());
        setPreviousXY(tempX, tempY);
    }

    @Override
    protected void updatePosition(MovementDirection direction) {
        setPreviousXY(super.getX(), super.getY());
        super.performMove(direction);
    }

    @Override
    public void gotHit() {
        super.setHP(getHP() - getDamageTaken());
        recoilXY();
    }

    public void setPowerUp(IPowerUp powerUp) {
        this.powerUps = powerUp;
    }

    public IPowerUp getPowerUp() {
        return this.powerUps;
    }

    public void setScoreBoost(boolean x) {
        this.scoreBoost = x;
    }

    public void setWallWalker(boolean x) {
        this.wallWalker = x;
    }

    public boolean canWalkThroughWalls() {
        return this.wallWalker;
    }

    public String listPowerUps() {
        return this.powerUps.listPowerUps();
    }
} // Player
