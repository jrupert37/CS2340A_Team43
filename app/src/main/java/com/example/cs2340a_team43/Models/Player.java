package com.example.cs2340a_team43.Models;

public class Player {
    //private float x;
    //private float y;
    private double damage;
    private int hp;

    private static Player player;

    private Player(double damage, int hp) {
        this.damage = damage;
        //this.y = y;
        this.hp = hp;
        //playerImageView = findViewById(R.id.playerImageView);
        //playerImageView.setImageResource(R.drawable.frowny);
    }

    public static Player getInstance(double damage, int hp) {
        if (player == null) {
            player = new Player(damage, hp);
        }
        return player;
    }
    public int getHP() {
        return hp;
    }

    public double getDamage() {
        return damage;
    }



}
