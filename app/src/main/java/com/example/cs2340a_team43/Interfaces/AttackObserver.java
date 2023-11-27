package com.example.cs2340a_team43.Interfaces;

/*
 * An AttackObserver is attacked by an Attacker class.
 * Update method will return true if the given xy location
 * matches the AttackObserver's xy location.
 */
public interface AttackObserver {
    boolean updateWithAttack(int x, int y);
} // AttackObserver (interface)
