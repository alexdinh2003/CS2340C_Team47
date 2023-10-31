package com.example.dungeoncrawling.model;

public class Enemy1Movement implements EnemyMovement{
    @Override
    //however you want this enemy to move
    public int[] move(Enemy e) {
        return new int[]{e.getRow() + 1, e.getCol()};
    }
}
