package com.example.dungeoncrawling.model;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class Enemy1 extends Enemy implements EnemySubscriber {
    private int moveCount = 0;
    private boolean moveRight = true;

    public Enemy1(int row, int col, SpriteSheet spriteSheet) {
        super(row, col, spriteSheet);
    }

    public Enemy1(int row, int col) {
        super(row, col);
    }

    public void createSprite() {
        this.sprite = this.getSpriteSheet().getEnemy(0);
    }

    public void move() {
        //change position in some way every 1/2 sec
        if (moveCount > 6) {
            moveCount = 0;
            moveRight = !moveRight;
        }
        if (moveRight) {
            setPosition(getRow() + 1, getCol());
        } else {
            setPosition(getRow() - 1, getCol());
        }
        moveCount += 1;
    }

    @Override
    public void setPosition(int row, int col) {
        super.setPosition(row, col);
    }
}
