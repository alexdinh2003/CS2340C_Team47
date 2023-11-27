package com.example.dungeoncrawling.model.playermove;

import com.example.dungeoncrawling.model.Player;

public class Up implements DirectionStrategy {
    //moves player up
    @Override
    public int[] move(Player p) {
        return new int[]{p.getRow() - 1, p.getCol()};
    }
}
