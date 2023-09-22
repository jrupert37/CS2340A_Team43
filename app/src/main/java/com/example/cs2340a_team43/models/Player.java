package com.example.cs2340a_team43.models;

public class Player {
    private float x;
    private float y;
    private int hp;

    public Player(float x, float y, int hp) {
        this.x = x;
        this.y = y;
        this.hp = hp;
        //playerImageView = findViewById(R.id.playerImageView);
        //playerImageView.setImageResource(R.drawable.frowny);
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }



}
