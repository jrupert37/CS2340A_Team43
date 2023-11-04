package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.lifecycle.ViewModel;

import com.example.cs2340a_team43.Models.MovementBehavior;
import com.example.cs2340a_team43.Models.Observer;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.Models.Subject;
import com.example.cs2340a_team43.Models.WalkMovement;

import java.util.ArrayList;
import java.util.List;

public class PlayerViewModel extends ViewModel implements Subject,Observer {
    private Player player;

    private static PlayerViewModel playerViewModel;
    private MapViewModel mapViewModel;
    private int initialX;
    private int initialY;
    private List<Observer> observers;
    private boolean notified;

    private PlayerViewModel() {
        this.player = Player.getInstance();
        observers = new ArrayList<>();
        notified = false;
    }

    public static PlayerViewModel getInstance() {
        if (playerViewModel == null) {
            return new PlayerViewModel();
        }
        return playerViewModel;
    }
    public Bitmap getPlayerBitmap() {
        return this.player.getBitmap();
    }

    public int getPlayerX() {
        return this.player.getX();
    }

    public int getPlayerY() {
        return this.player.getY();
    }

    public int getPlayerSpeed() {
        return this.player.getSpeed();
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    public void setPlayerName(String playerName) {
        this.player.setName(playerName);
    }

    public void setPlayerHP(String difficulty) {
        this.player.setHP(difficulty);
    }

    public void setInitialPlayerXY(int x, int y) {
        initialX = x;
        initialY = y;
        this.player.setInitialXY(initialX, initialY);
    }

    public void resetPlayerXY() {
        this.player.setInitialXY(initialX, initialY);
    }

    public void setImageId(int imageId, Context context) {
        this.player.setImageId(imageId, context);
    }

    public void setMap(MapViewModel mapViewModel) {
        this.mapViewModel = mapViewModel;
    }

    public void movePlayerLeft() {
        if (willCollideWithWall(getPlayerX() - getPlayerSpeed(), getPlayerY())) {
            return;
        }
        // otherwise...
        this.player.moveLeft();
        notifyObservers();
    }

    public void movePlayerRight() {
        if (willCollideWithWall(getPlayerX() + getPlayerSpeed(), getPlayerY())) {
            return;
        }
        // otherwise...
        this.player.moveRight();
        notifyObservers();
    }

    public void movePlayerUp() {
        if (willCollideWithWall(getPlayerX(), getPlayerY() - getPlayerSpeed())) {
            return;
        }
        // otherwise...
        this.player.moveUp();
        notifyObservers();
    }

    public void movePlayerDown() {
        if (willCollideWithWall(getPlayerX(), getPlayerY() + getPlayerSpeed())) {
            return;
        }
        // otherwise...
        this.player.moveDown();
        notifyObservers();
    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        System.out.println("OVER HERE");
        this.notified = true;
        for (Observer o: observers) {
            o.update();
        }
        System.out.println("NOTIFIED #1: " + notified);
    }

    public boolean isNotified() {
        return notified;
    }


    public boolean willCollideWithWall(int newX, int newY) {
        return mapViewModel.isAWall(newX, newY);
    }

    public boolean playerIsAtExit() {
        return mapViewModel.xyIsAnExit(getPlayerX(), getPlayerY());
    }

    public int getPlayerHP() {
        return this.player.getHp();
    }

    public void update() {}

    public void setPlayerMovementBehavior(MovementBehavior behavior) {
        this.player.setMovementBehavior(behavior);
    }
} // PlayerViewModel

