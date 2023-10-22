package com.example.dungeoncrawling.model;

public class Right implements DirectionStrategy {
    @Override
    public int[] move(Player p) {
        return new int[]{p.getRow(), p.getCol() + 1};
    }
}

