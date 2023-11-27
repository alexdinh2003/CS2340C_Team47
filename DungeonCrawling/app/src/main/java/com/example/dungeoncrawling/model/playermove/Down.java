package com.example.dungeoncrawling.model.playermove;

import com.example.dungeoncrawling.model.Player;

public class Down implements DirectionStrategy {
    //moves player down
    @Override
    public int[] move(Player p) {
        return new int[]{p.getRow() + 1, p.getCol()};
    }
}
