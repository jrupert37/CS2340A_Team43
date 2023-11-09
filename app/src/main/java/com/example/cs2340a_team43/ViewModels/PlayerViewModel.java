package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.cs2340a_team43.Interfaces.CollisionObserver;
import com.example.cs2340a_team43.Interfaces.MovementBehavior;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.Interfaces.Subject;
import com.example.cs2340a_team43.Interfaces.ViewObserver;
import java.util.ArrayList;
import java.util.List;

public class PlayerViewModel extends CharacterViewModel implements Subject, CollisionObserver {
    private final Player player;
    private static PlayerViewModel playerViewModel;
    private MapViewModel mapViewModel;
    private int initialX;
    private int initialY;
    private final List<CollisionObserver> collisionObservers;
    private final List<ViewObserver> viewObservers;
    private final boolean notified;
    private int xBound;
    private int yBound;

    private PlayerViewModel() {
        super(Player.getInstance());
        this.player = Player.getInstance();
        collisionObservers = new ArrayList<>();
        viewObservers = new ArrayList<>();
        xBound = 0;
        yBound = 0;
        notified = false;
    }

    public static PlayerViewModel getInstance() {
        if (playerViewModel == null) {
            return new PlayerViewModel();
        }
        return playerViewModel;
    }

    public Bitmap getPlayerSprite() {
        return this.player.getSprite();
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

    public void setPlayerInitialHP(String difficulty) {
        this.player.setInitialHP(difficulty);
    }

    //    public void setPlayerHP(int hp) {
    //        this.player.setHP(hp);
    //    }

    public void setInitialPlayerXY(int x, int y) {
        initialX = x;
        initialY = y;
        this.player.setInitialXY(initialX, initialY);
    }

    public void resetPlayerXY() {
        this.player.setInitialXY(initialX, initialY);
        notifyWithPosition();
        notifyViewObservers();
    }

    public void setSprite(int imageId, Context context) {
        this.player.setSprite(imageId, context);
    }

    public void setMap(MapViewModel mapViewModel) {
        this.mapViewModel = mapViewModel;
    }

    public void movePlayerLeft() {
        if (willBeOutOfBounds(getPlayerX() - getPlayerSpeed(), getPlayerY())
                || willCollideWithWall(getPlayerX() - getPlayerSpeed(), getPlayerY())) {
            return;
        }
        //        else if (willCollideWithEnemy(getPlayerX() - getPlayerSpeed(), getPlayerY())) {
        //            notifyAllObservers();
        //            return;
        //        }
        // otherwise...
        this.player.moveLeft();
        notifyWithPosition();
        notifyViewObservers();
    }

    public void movePlayerRight() {
        if (willBeOutOfBounds(getPlayerX() + getPlayerSpeed(), getPlayerY())
                || willCollideWithWall(getPlayerX() + getPlayerSpeed(), getPlayerY())) {
            return;
        }
        //        else if (willCollideWithEnemy(getPlayerX() + getPlayerSpeed(), getPlayerY())) {
        //            notifyAllObservers();
        //            return;
        //        }
        this.player.moveRight();
        notifyWithPosition();
        notifyViewObservers();
    }

    public void movePlayerUp() {
        if (willBeOutOfBounds(getPlayerX(), getPlayerY() - getPlayerSpeed())
                || willCollideWithWall(getPlayerX(), getPlayerY() - getPlayerSpeed())) {
            return;
        }
        //        else if (willCollideWithEnemy(getPlayerX(), getPlayerY() - getPlayerSpeed())) {
        //            notifyAllObservers();
        //            return;
        //        }
        // otherwise...
        this.player.moveUp();
        notifyWithPosition();
        notifyViewObservers();
    }

    public void movePlayerDown() {
        if (willBeOutOfBounds(getPlayerX(), getPlayerY() + getPlayerSpeed())
                || willCollideWithWall(getPlayerX(), getPlayerY() + getPlayerSpeed())) {
            return;
        }
        //        else if (willCollideWithEnemy(getPlayerX(), getPlayerY() + getPlayerSpeed())) {
        //            notifyAllObservers();
        //            return;
        //        }
        // otherwise...
        this.player.moveDown();
        notifyWithPosition();
        notifyViewObservers();
    }

    @Override
    public void notifyWithPosition() {
        for (CollisionObserver co : collisionObservers) {
            if (co.updateWithPosition(getPlayerX(), getPlayerY())) {
                this.gotHit();
            }
        }
    }

    @Override
    public boolean updateWithPosition(int x, int y) {
        if (getPlayerX() == x && getPlayerY() == y) {
            this.gotHit();
            return true;
        }
        return false;
    }

    @Override
    public void notifyViewObservers() {
        for (ViewObserver vo : viewObservers) {
            vo.update();
        }
    }

    @Override
    public void addViewObserver(ViewObserver vo) {
        this.viewObservers.add(vo);
    }

    @Override
    public void addCollisionObserver(CollisionObserver co) {
        this.collisionObservers.add(co);
    }

    @Override
    public void removeCollisionObserver(CollisionObserver co) {
        this.collisionObservers.remove(co);
    }

    @Override
    public void removeViewObserver(ViewObserver vo) {
        this.viewObservers.remove(vo);
    }

    public boolean isNotified() {
        return notified;
    }

    private boolean willCollideWithWall(int newX, int newY) {
        return mapViewModel.isAWall(newX, newY);
    }


    public boolean playerIsAtExit() {
        return mapViewModel.xyIsAnExit(getPlayerX(), getPlayerY());
    }

    public int getPlayerHP() {
        return this.player.getHP();
    }

    public void setPlayerMovementBehavior(MovementBehavior behavior) {
        this.player.setMovementBehavior(behavior);
    }

    public boolean isAlive() {
        return getPlayerHP() > 0;
    }

    public void setXYBounds(int x, int y) {
        this.xBound = x;
        this.yBound = y;
    }

    public boolean willBeOutOfBounds(int x, int y) {
        return (x < 0 || x > xBound || y < 0 || y > yBound);
    }
} // PlayerViewModel

