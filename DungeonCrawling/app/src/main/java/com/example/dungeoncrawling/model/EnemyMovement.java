package com.example.dungeoncrawling.model;

public interface EnemyMovement {
    public default int[] move(Enemy e) {
        return new int[]{};
    }
}
