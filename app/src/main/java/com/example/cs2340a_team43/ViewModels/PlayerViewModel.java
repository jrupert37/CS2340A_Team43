package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.cs2340a_team43.Models.CollisionObserver;
import com.example.cs2340a_team43.Models.MovementBehavior;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.Models.Subject;
import com.example.cs2340a_team43.Models.ViewObserver;
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
    private boolean notified;

    private PlayerViewModel() {
        super(Player.getInstance());
        this.player = Player.getInstance();
        collisionObservers = new ArrayList<>();
        viewObservers = new ArrayList<>();
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
        notifyOfPosition();
        notifyViewObservers();
    }

    public void setSprite(int imageId, Context context) {
        this.player.setSprite(imageId, context);
    }

    public void setMap(MapViewModel mapViewModel) {
        this.mapViewModel = mapViewModel;
    }

    public void movePlayerLeft() {
        if (willCollideWithWall(getPlayerX() - getPlayerSpeed(), getPlayerY())) {
            return;
        }
//        else if (willCollideWithEnemy(getPlayerX() - getPlayerSpeed(), getPlayerY())) {
//            notifyAllObservers();
//            return;
//        }
        // otherwise...
        this.player.moveLeft();
        notifyOfPosition();
        notifyViewObservers();
    }

    public void movePlayerRight() {
        if (willCollideWithWall(getPlayerX() + getPlayerSpeed(), getPlayerY())) {
            return;
        }
//        else if (willCollideWithEnemy(getPlayerX() + getPlayerSpeed(), getPlayerY())) {
//            notifyAllObservers();
//            return;
//        }
        this.player.moveRight();
        notifyOfPosition();
        notifyViewObservers();
    }

    public void movePlayerUp() {
        if (willCollideWithWall(getPlayerX(), getPlayerY() - getPlayerSpeed())) {
            return;
        }
//        else if (willCollideWithEnemy(getPlayerX(), getPlayerY() - getPlayerSpeed())) {
//            notifyAllObservers();
//            return;
//        }
        // otherwise...
        this.player.moveUp();
        notifyOfPosition();
        notifyViewObservers();
    }

    public void movePlayerDown() {
        if (willCollideWithWall(getPlayerX(), getPlayerY() + getPlayerSpeed())) {
            return;
        }
//        else if (willCollideWithEnemy(getPlayerX(), getPlayerY() + getPlayerSpeed())) {
//            notifyAllObservers();
//            return;
//        }
        // otherwise...
        this.player.moveDown();
        notifyOfPosition();
        notifyViewObservers();
    }

    @Override
    public void notifyOfPosition() {
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

    public int getPreviousX() {
        return this.player.getPreviousX();
    }

    public int getPreviousY() {
        return this.player.getPreviousY();
    }

    public boolean isAlive() {
        return getPlayerHP() > 0;
    }



} // PlayerViewModel

