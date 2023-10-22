package com.example.dungeoncrawling.model;

public class Down implements DirectionStrategy {
    @Override
    public void move(Player p) {
        int[] newLoc = {p.getRow() + 1, p.getCol()};
        p.setPositionArr(newLoc);
    }
}
