package com.example.cs2340a_team43.Models;

import com.example.cs2340a_team43.ViewModels.CharacterViewModel;

/*
 * This interface is used by player, enemies, and the game view.
 * Player will be an observer of enemies, enemies will be an observer of player,
 * GameView will be an observer of player and enemies.
 * This interface is useful for implementing the Observer Design Pattern.
 */
public interface CollisionObserver {
    public boolean updateWithPosition(int x, int y);
} // CollisionObserver (interface)
