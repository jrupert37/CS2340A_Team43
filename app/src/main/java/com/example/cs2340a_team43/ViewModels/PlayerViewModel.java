package com.example.cs2340a_team43.ViewModels;

import android.content.Context;
import android.graphics.Bitmap;
import com.example.cs2340a_team43.Interfaces.AttackObserver;
import com.example.cs2340a_team43.Interfaces.Attacker;
import com.example.cs2340a_team43.Interfaces.CollisionObserver;
import com.example.cs2340a_team43.Interfaces.MovementBehavior;
import com.example.cs2340a_team43.Models.HealthDecorator;
import com.example.cs2340a_team43.Models.Player;
import com.example.cs2340a_team43.Interfaces.Subject;
import com.example.cs2340a_team43.Interfaces.ViewObserver;
import com.example.cs2340a_team43.Models.ScoreBoostDecorator;
import com.example.cs2340a_team43.Models.WallWalkerDecorator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerViewModel extends CharacterViewModel implements Subject,
        Attacker, CollisionObserver {
    private final Player player;
    private static PlayerViewModel playerViewModel;
    private MapViewModel mvm;
    private int initialX;
    private int initialY;
    private final List<CollisionObserver> collisionObservers;
    private final List<ViewObserver> viewObservers;
    private final List<AttackObserver> attackObservers;
    private final boolean notified;
    private int xLimit;
    private int yLimit;

    private PlayerViewModel() {
        super(Player.getInstance());
        this.player = Player.getInstance();
        collisionObservers = new CopyOnWriteArrayList<>();
        viewObservers = new CopyOnWriteArrayList<>();
        attackObservers = new CopyOnWriteArrayList<>();
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
        checkAndNotify();
    }

    public void movePlayerRight() {
        if (checkBoundsAndWalls(getPlayerX() + getPlayerSpeed(), getPlayerY())) {
            return;
        }
        // otherwise...
        this.player.moveRight();
        checkAndNotify();
    }

    public void movePlayerUp() {
        if (checkBoundsAndWalls(getPlayerX(), getPlayerY() - getPlayerSpeed())) {
            return;
        }
        // otherwise...
        this.player.moveUp();
        checkAndNotify();
    }

    public void movePlayerDown() {
        if (checkBoundsAndWalls(getPlayerX(), getPlayerY() + getPlayerSpeed())) {
            return;
        }
        // otherwise...
        this.player.moveDown();
        checkAndNotify();
    }

    private void checkAndNotify() {
        checkIfObtainedPowerUp();
        checkIfObtainedKey();
        notifyMoved();
    }

    public void attackUp() {
        if (willBeOutOfBounds(getPlayerX(), getPlayerY() - 1)) {
            return;
        }
        notifyWithAttack(getPlayerX(), getPlayerY() - 1);
    }

    public void attackDown() {
        if (willBeOutOfBounds(getPlayerX(), getPlayerY() + 1)) {
            return;
        }
        notifyWithAttack(getPlayerX(), getPlayerY() + 1);
    }

    public void attackLeft() {
        if (willBeOutOfBounds(getPlayerX() - 1, getPlayerY())) {
            return;
        }
        notifyWithAttack(getPlayerX() - 1, getPlayerY());
    }

    public void attackRight() {
        if (willBeOutOfBounds(getPlayerX() + 1, getPlayerY())) {
            return;
        }
        notifyWithAttack(getPlayerX() + 1, getPlayerY());
    }

    public void testMovePlayerDown() {
        if (checkBoundsAndWalls(getPlayerX(), getPlayerY() + getPlayerSpeed())) {
            return;
        }
        // otherwise...
        this.player.moveDown();
        notifyMoved();
    }

    private boolean checkBoundsAndWalls(int x, int y) {
        return (willBeOutOfBounds(x, y)
                || (!player.canWalkThroughWalls() && willCollideWithWall(x, y)));
    }

    private void notifyMoved() {
        notifyWithPosition();
        notifyViewObservers();
    }

    @Override
    public void addAttackObserver(AttackObserver ao) {
        attackObservers.add(ao);
    }

    @Override
    public void removeAttackObserver(AttackObserver ao) {
        attackObservers.remove(ao);
    }

    @Override
    public void notifyWithAttack(int x, int y) {
        for (AttackObserver ao : attackObservers) {
            if (ao.updateWithAttack(x, y)) {
                player.setScore(player.getScore() + player.attackBonus());
            }
        }
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

    private boolean willCollideWithWall(int newX, int newY) {
        return mvm.isAWall(newX, newY);
    }


    public boolean playerIsAtExit() {
        return mvm.xyIsAnExit(getPlayerX(), getPlayerY());
    }
    public boolean playerCanLeave() {
        return (playerIsAtExit() && player.hasKey());
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

    private void checkIfObtainedPowerUp() {
        if (mvm.isAPowerUp(getPlayerX(), getPlayerY())) {
            String type = mvm.getThisFloorsPowerUp().getType();
            if (type.equals("score boost")) {
                player.setPowerUp(new ScoreBoostDecorator(player.getPowerUp()));
            } else if (type.equals("wall walker")) {
                player.setPowerUp(new WallWalkerDecorator(player.getPowerUp()));
            } else {
                player.setPowerUp(new HealthDecorator(player.getPowerUp()));
                player.setHP(player.getHP() + 5);
            }
            mvm.powerUpIsTaken();
        }
    }


    public void attainScoreBoost() {
        player.setPowerUp(new ScoreBoostDecorator(player.getPowerUp()));
        //player.setScoreBoost(true);
    }
    public void attainWallWalker() {
        player.setPowerUp(new WallWalkerDecorator(player.getPowerUp()));
        //player.setWallWalker(true);
    }
    public void attainHealth() {
        player.setPowerUp(new HealthDecorator(player.getPowerUp()));
        player.setHP(player.getHP() + 10);
    }

    private void checkIfObtainedKey() {
        if (mvm.isAKey(getPlayerX(), getPlayerY())) {
            player.doesHaveKey(true);
        }
    }

    public String listPowerUps() {
        return this.player.listPowerUps();
    }

    public void resetPowerUps() {
        this.player.resetPowerUps();
    }

    public void resetScore() {
        this.player.resetScore();
    }

    public void doesHaveKey(boolean hasKey) {
        this.player.doesHaveKey(hasKey);
    }

    public int getScore() {
        return this.player.getScore();
    }

    public void setScore(int newScore) {
        this.player.setScore(newScore);
    }

    protected void gotHit() {
        this.player.gotHit();
    }

    public String getDifficulty() {
        return player.getDifficulty();
    }

    public boolean canWalkThroughWalls() {
        return this.player.canWalkThroughWalls();
    }

    public boolean hasScoreBoost() {
        return player.hasScoreBoost();
    }

    public boolean hasKey() {
        return player.hasKey();
    }
} // PlayerViewModel
