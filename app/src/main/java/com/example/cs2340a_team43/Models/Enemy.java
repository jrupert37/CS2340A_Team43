package com.example.cs2340a_team43.Models;

/*
 * This is a parent class for all Enemy types. This class extends the Character parent class.
*/
public abstract class Enemy extends Character {
    private boolean isAttacked;

    protected Enemy() {
        super();
        isAttacked = false;
    }

    public void setAttacked() {
        isAttacked = true;
    }

    public boolean isAttacked() {
        return isAttacked;
    }
} // Enemy (abstract parent)
