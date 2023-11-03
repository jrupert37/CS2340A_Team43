package com.example.cs2340a_team43.Models;

public interface Subject {
    public void addObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
