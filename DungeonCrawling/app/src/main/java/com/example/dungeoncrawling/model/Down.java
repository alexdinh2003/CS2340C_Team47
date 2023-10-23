package com.example.dungeoncrawling.model;
import com.example.dungeoncrawling.model.graphics.Sprite;
public class Down implements DirectionStrategy {
    @Override
    public void move(Player p) {
        int[] newLoc = {p.getRow() + 1, p.getCol()};
        p.setPositionArr(newLoc);
    }
}
