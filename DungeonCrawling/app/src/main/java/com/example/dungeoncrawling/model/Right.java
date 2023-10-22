package com.example.dungeoncrawling.model;

public class Right implements DirectionStrategy {
    @Override
    public void move(Player p) {
        int[] newLoc = {p.getRow(), p.getCol() + 1};
        p.setPositionArr(newLoc);
    }
}

