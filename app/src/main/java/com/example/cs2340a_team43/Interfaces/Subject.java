package com.example.cs2340a_team43.Interfaces;

/*
 * This interface is used by the player and enemy.
 * This interface is useful for implementing the Observer Design Pattern.
 */
public interface Subject {
    void addCollisionObserver(CollisionObserver co);
    void addViewObserver(ViewObserver vo);
    void removeCollisionObserver(CollisionObserver co);
    void removeViewObserver(ViewObserver vo);
    void notifyWithPosition();
    void notifyViewObservers();
} // Subject (interface)
