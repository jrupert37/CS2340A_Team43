package com.example.cs2340a_team43.Interfaces;

/*
 * An interface useful for characters that need to attack others.
 * Attackers will make an attack in a particular location
 * and this position is passed into the notify method.
 */
public interface Attacker {
    void addAttackObserver(AttackObserver ao);
    void removeAttackObserver(AttackObserver ao);
    void notifyWithAttack(int x, int y);
} // Attacker (interface)
