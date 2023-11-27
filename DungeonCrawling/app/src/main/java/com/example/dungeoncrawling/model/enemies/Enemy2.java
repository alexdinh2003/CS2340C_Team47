package com.example.dungeoncrawling.model.enemies;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class Enemy2 extends Enemy implements EnemySubscriber {
    private int moveCount = 0;
    private static final double SPEED = 0.5;
    private double partialXPos;
    private boolean moveRight = true;
    public Enemy2(int row, int col, SpriteSheet spriteSheet) {
        super(row, col, spriteSheet);
        partialXPos = col;
    }

    public Enemy2(int row, int col) {
        super(row, col);
        partialXPos = col;
    }

    public void createSprite() {
        this.sprite = this.getSpriteSheet().getEnemy(1);
    }

    //moves left and right
    public void move() {
        if (moveCount > 8) {
            moveCount = 0;
            moveRight = !moveRight;
        }
        if (moveRight) {
            partialXPos += SPEED;
            setPosition(getRow(), (int) partialXPos);
        } else {
            partialXPos -= SPEED;
            setPosition(getRow(), (int) partialXPos);

        }
        moveCount += 1;

    }
}
