package com.example.dungeoncrawling.model;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class Enemy4 extends Enemy implements EnemySubscriber {

    public Enemy4(int row, int col, SpriteSheet spriteSheet) {
        super(row, col, spriteSheet);
    }

    public Enemy4(int row, int col) {
        super(row, col);
    }

    public void createSprite() {
        this.sprite = this.getSpriteSheet().getEnemy(3);
    }

    public void move() {
        //change position in some way every 1/2 sec
    }

    @Override
    public void update(EnemyPlayerCollision subject) {
        if (subject.getRow() == this.getRow() && subject.getCol() == this.getCol()) {
            getPlayer().loseHealth(2);
        }
    }
}
