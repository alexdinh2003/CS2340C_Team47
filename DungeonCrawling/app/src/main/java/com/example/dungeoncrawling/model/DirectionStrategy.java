package com.example.dungeoncrawling.model;
import com.example.dungeoncrawling.model.Player;

public interface DirectionStrategy {
    abstract void move(Player p);
}
