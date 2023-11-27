package com.example.cs2340a_team43.Models;

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
    private final XYPair previousXY;
    private IPowerUp powerUps;
    private int score;
    private String difficulty;
    protected int hp;
    protected int damageTaken;
    private boolean hasKey;

    /* Private constructor to prevent other classes from instantiating a new player */
    private Player() {
        super(); // calls the Character constructor
        // player attributes will be more concretely initialized later
        previousXY = new XYPair(1, 1);
        this.bitmap = null;
        this.damageTaken = 0;
        this.score = 0;
        this.hasKey = false;
        resetPowerUps();
    }

    public void resetPowerUps() {
        powerUps = new PowerUpDecorator();
    }

    public void resetScore() {
        this.score = 0;
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
        if (playerName == null || playerName.trim().equals("")) {
            throw new IllegalStateException();
        }
    }

    public void setInitialHP(String difficulty) {
        this.difficulty = difficulty;
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


    public int getPreviousX() {
        return this.previousXY.x();
    }

    public int getPreviousY() {
        return this.previousXY.y();
    }

    private void setPreviousXY(int x, int y) {
        this.previousXY.setXY(x, y);
    }

    private void recoilXY() {
        int tempX = super.getX();
        int tempY = super.getY();
        super.xyCoordinates.setXY(getPreviousX(), getPreviousY());
        setPreviousXY(tempX, tempY);
    }

    @Override
    protected void updatePosition(MovementDirection direction) {
        setPreviousXY(super.getX(), super.getY());
        super.performMove(direction);
    }

    public void gotHit() {
        setHP(getHP() - getDamageTaken());
        recoilXY();
    }

    public void setPowerUp(IPowerUp powerUp) {
        this.powerUps = powerUp;
    }

    public IPowerUp getPowerUp() {
        return this.powerUps;
    }

    public boolean canWalkThroughWalls() {
        return listPowerUps().contains("Wall Walker");
    }

    public boolean hasScoreBoost() {
        return listPowerUps().contains("Atk Score Boost");
    }

    public boolean hasKey() {
        return this.hasKey;
    }

    public void doesHaveKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public String listPowerUps() {
        return this.powerUps.listPowerUps();
    }

    public void setScore(int newScore) {
        this.score = newScore;
    }

    public int getScore() {
        return this.score;
    }

    public int attackBonus() {
        if (hasScoreBoost()) {
            return 10;
        }
        return 5;
    }

    public int getHP() {
        return this.hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    protected int getDamageTaken() {
        return this.damageTaken;
    }

    public String getDifficulty() {
        return this.difficulty;
    }
} // Player (Character child class)
