package com.example.dungeoncrawling.model;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class Enemy2 extends Enemy implements EnemySubscriber {

    public Enemy2(int row, int col, SpriteSheet spriteSheet) {
        super(row, col, spriteSheet);
    }

    public Enemy2(int row, int col) {
        super(row, col);
    }

    public void createSprite() {
        this.sprite = this.getSpriteSheet().getEnemy(1);
    }

    public void move() {
        //change position in some way every 1/2 sec
    }
}
