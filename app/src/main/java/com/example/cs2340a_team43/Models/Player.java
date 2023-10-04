package com.example.cs2340a_team43.Models;

public class Player {
    //private float x;
    //private float y;
    private double damage;
    private int hp;

    private static Player player;

    private Player() {
        this.damage = 0.0;
        //this.y = y;
        this.hp = 0;
        //playerImageView = findViewById(R.id.playerImageView);
        //playerImageView.setImageResource(R.drawable.frowny);
    }

    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }
    public int getHP() {
        return hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
    public double getDamage() {
        return damage;
    }



}
