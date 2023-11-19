package com.example.dungeoncrawling.model.playermove;

import com.example.dungeoncrawling.model.Player;

public interface DirectionStrategy {
    abstract int[] move(Player p);
}
