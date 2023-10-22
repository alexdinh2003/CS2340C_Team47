package com.example.dungeoncrawling.model;

public class Up implements DirectionStrategy {
    @Override
    public int[] move(Player p) {
        return new int[]{p.getRow() - 1, p.getCol()};
    }
}
