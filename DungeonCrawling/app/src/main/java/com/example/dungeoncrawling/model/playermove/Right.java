package com.example.dungeoncrawling.model.playermove;

import com.example.dungeoncrawling.model.Player;

public class Right implements DirectionStrategy {
    //moves player right
    @Override
    public int[] move(Player p) {
        return new int[]{p.getRow(), p.getCol() + 1};
    }
}

