package com.example.cs2340a_team43.Models;

public class XYPair {
    private int x;
    private int y;

    public XYPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
} // XYPair
