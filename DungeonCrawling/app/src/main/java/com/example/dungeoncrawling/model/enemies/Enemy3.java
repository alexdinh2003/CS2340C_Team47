package com.example.dungeoncrawling.model.enemies;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class Enemy3 extends Enemy implements EnemySubscriber {
    private int moveCount = 0;
    private static final double SPEED = 0.75;
    private double partialXPos;
    private double partialYPos;
    private boolean moveRight = true;
    public Enemy3(int row, int col, SpriteSheet spriteSheet) {
        super(row, col, spriteSheet);
        partialXPos = col;
        partialYPos = row;
    }

    public Enemy3(int row, int col) {
        super(row, col);
        partialXPos = col;
        partialYPos = row;
    }

    public void createSprite() {
        this.sprite = this.getSpriteSheet().getEnemy(2);
    }

    //moves diagonal
    public void move() {
        //change position in some way every 1/2 sec
        if (moveCount > 4) {
            moveCount = 0;
            moveRight = !moveRight;
        }
        if (moveRight) {
            partialXPos += SPEED;
            partialYPos += SPEED;
            setPosition((int) partialYPos, (int) partialXPos);
        } else {
            partialXPos -= SPEED;
            partialYPos -= SPEED;
            setPosition((int) partialYPos, (int) partialXPos);
        }
        moveCount += 1;
    }
}
