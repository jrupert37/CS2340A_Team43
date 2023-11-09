package com.example.cs2340a_team43.Interfaces;

/*
 * This interface is used by player, enemies, and the game view.
 * Player will be an observer of enemies, enemies will be an observer of player,
 * GameView will be an observer of player and enemies.
 * This interface is useful for implementing the Observer Design Pattern.
 */
public interface CollisionObserver {
    boolean updateWithPosition(int x, int y);
} // CollisionObserver (interface)
