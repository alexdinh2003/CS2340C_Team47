package com.example.dungeoncrawling.model.enemies;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class Enemy1 extends Enemy implements EnemySubscriber {
    private int moveCount = 0;
    private static final double SPEED = 0.25;
    private double partialYPos;
    private boolean moveRight = true;

    public Enemy1(int row, int col, SpriteSheet spriteSheet) {
        super(row, col, spriteSheet);
        partialYPos = row;
    }

    public Enemy1(int row, int col) {
        super(row, col);
        partialYPos = row;
    }

    public void createSprite() {
        this.sprite = this.getSpriteSheet().getEnemy(0);
    }

    //moves left and right
    public void move() {
        //change position in some way every 1/2 sec
        if (moveCount > 24) {
            moveCount = 0;
            moveRight = !moveRight;
        }
        if (moveRight) {
            partialYPos += SPEED;
            setPosition((int) partialYPos, getCol());
        } else {
            partialYPos -= SPEED;
            setPosition((int) partialYPos, getCol());
        }
        moveCount += 1;
    }

    @Override
    public void setPosition(int row, int col) {
        super.setPosition(row, col);
    }
}
