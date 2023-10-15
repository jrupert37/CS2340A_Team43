package com.example.cs2340a_team43.Models;

public class Player {
    private int x;
    private int y;
    private double damage;
    private int hp;

    private static Player player;
    private String name;

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

    /*public void setHP(int hp) {
        this.hp = hp;
    }*/
    public void setHP(String difficulty) {
        if (difficulty.equals("Easy")) {
            hp = 50;
        } else if (difficulty.equals("Medium")) {
            hp = 30;
        } else {
            hp = 15;
        }
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
    public double getDamage() {
        return damage;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        if (name == null || name == "") {
            throw new IllegalStateException();
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
