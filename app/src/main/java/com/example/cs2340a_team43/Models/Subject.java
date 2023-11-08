package com.example.cs2340a_team43.Models;

/*
 * This interface is used by the player and enemy.
 * This interface is useful for implementing the Observer Design Pattern.
 */
public interface Subject {
    public void addCollisionObserver(CollisionObserver co);
    public void addViewObserver(ViewObserver vo);
    public void removeCollisionObserver(CollisionObserver co);
    public void removeViewObserver(ViewObserver vo);
    public void notifyWithPosition();
    public void notifyViewObservers();
} // Subject (interface)
