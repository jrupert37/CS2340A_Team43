package com.example.cs2340a_team43.Interfaces;

/*
* This interface is specifically for Enemies.
* This allows the GameActivity to call simple start and stop functions on each floor's
* enemy movements.
* One floor's enemies are "stopped", then the next floor's are "started".
*/
public interface ExecutableMovementPattern {
    void start();
    void stop();
} // ExecutableMovementPattern (interface)
