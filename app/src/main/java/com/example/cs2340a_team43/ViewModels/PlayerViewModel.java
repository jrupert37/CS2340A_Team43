package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.cs2340a_team43.Interfaces.CollisionObserver;
import com.example.cs2340a_team43.Interfaces.MovementBehavior;
import com.example.cs2340a_team43.Models.HealthDecorator;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.Interfaces.Subject;
import com.example.cs2340a_team43.Interfaces.ViewObserver;
import com.example.cs2340a_team43.Models.ScoreBoostDecorator;
import com.example.cs2340a_team43.Models.WallWalkerDecorator;
import java.util.ArrayList;
import java.util.List;

public class PlayerViewModel extends CharacterViewModel implements Subject, CollisionObserver {
    private final Player player;
    private static PlayerViewModel playerViewModel;
    private MapViewModel mvm;
    private int initialX;
    private int initialY;
    private final List<CollisionObserver> collisionObservers;
    private final List<ViewObserver> viewObservers;
    private final boolean notified;
    private int xLimit;
    private int yLimit;

    private PlayerViewModel() {
        super(Player.getInstance());
        this.player = Player.getInstance();
        collisionObservers = new ArrayList<>();
        viewObservers = new ArrayList<>();
        xLimit = 0;
        yLimit = 0;
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

    public void setMap(MapViewModel mvm) {
        this.mvm = mvm;
    }

    public void movePlayerLeft() {
        if (checkBoundsAndWalls(getPlayerX() - getPlayerSpeed(), getPlayerY())) {
            return;
        }
        // otherwise...
        this.player.moveLeft();
        notifyAllObservers();
        checkObtainedPowerUp();
    }

    public void movePlayerRight() {
        if (checkBoundsAndWalls(getPlayerX() + getPlayerSpeed(), getPlayerY())) {
            return;
        }
        // otherwise...
        this.player.moveRight();
        notifyAllObservers();
        checkObtainedPowerUp();
    }

    public void movePlayerUp() {
        if (checkBoundsAndWalls(getPlayerX(), getPlayerY() - getPlayerSpeed())) {
            return;
        }
        // otherwise...
        this.player.moveUp();
        notifyAllObservers();
        checkObtainedPowerUp();
    }

    public void movePlayerDown() {
        if (checkBoundsAndWalls(getPlayerX(), getPlayerY() + getPlayerSpeed())) {
            return;
        }
        // otherwise...
        this.player.moveDown();
        notifyAllObservers();
        checkObtainedPowerUp();
    }
    
    private boolean checkBoundsAndWalls(int x, int y) {
        return (willBeOutOfBounds(x, y)
                || (!player.canWalkThroughWalls() && willCollideWithWall(x, y)));
    }
    
    private void notifyAllObservers() {
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
        return mvm.isAWall(newX, newY);
    }


    public boolean playerIsAtExit() {
        return mvm.xyIsAnExit(getPlayerX(), getPlayerY());
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
        this.xLimit = x;
        this.yLimit = y;
    }

    public boolean willBeOutOfBounds(int x, int y) {
        return (x < 0 || x > xLimit || y < 0 || y > yLimit);
    }
    
    private void checkObtainedPowerUp() {
        if (mvm.isAPowerUp(getPlayerX(), getPlayerY())) {
            String type = mvm.getThisFloorsPowerUp().getType();
            if (type.equals("score boost")) {
                attainScoreBoost();
            } else if (type.equals("wall walker")) {
                attainWallWalker();
            } else { // type.equals("health")
                attainHealth();
            }
        }
    }
    public void attainScoreBoost(){
        player.setPowerUp(new ScoreBoostDecorator(player.getPowerUp()));
        player.setScoreBoost(true);
    }
    public void attainWallWalker(){
        player.setPowerUp(new WallWalkerDecorator(player.getPowerUp()));
        player.setWallWalker(true);
    }
    public void attainHealth(){
        player.setPowerUp(new HealthDecorator(player.getPowerUp()));
        player.setHP(player.getHP() + 10);
    }

    public String listPowerUps() {
        return this.player.listPowerUps();
    }

    public void resetPowerUps() {
        this.player.resetPowerUps();
    }
} // PlayerViewModel

