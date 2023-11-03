package com.example.cs2340a_team43.Models;

public class EnemyFactory extends EnemySpawner {
    protected Enemy createEnemy(String difficulty, String type) {
        if(type.equals("cat")){
            return new EnemyCat(difficulty);
        } else if(type.equals("grimreaper")){
            return new EnemyGrimReaper(difficulty);
        } else if(type.equals("skeleton")){
            return new EnemySkeleton(difficulty);
        } else if(type.equals("eyeball")) {
            return new EnemyEyeball(difficulty);
        } else {
            return null;
        }
    }
}
