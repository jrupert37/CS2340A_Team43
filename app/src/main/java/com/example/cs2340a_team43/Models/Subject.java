package com.example.cs2340a_team43.Models;

/*
 * This interface is used by the player and enemy.
 * This interface is useful for implementing the Observer Design Pattern.
 */
public interface Subject {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
} // Subject (interface)
