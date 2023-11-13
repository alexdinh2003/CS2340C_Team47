package com.example.dungeoncrawling.model;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class Enemy4 extends Enemy implements EnemySubscriber {
    private String dir = "down";
    private static final double SPEED = 1;
    private int moveCount = 0;

    public Enemy4(int row, int col, SpriteSheet spriteSheet) {
        super(row, col, spriteSheet);
    }

    public Enemy4(int row, int col) {
        super(row, col);
    }

    public void createSprite() {
        this.sprite = this.getSpriteSheet().getEnemy(3);
    }

    /** @noinspection checkstyle:FallThrough*/
    public void move() {
        //change position in some way every 1/2 secif (moveCount > 7) {
        if (moveCount > 5) {
            switch (dir) {
            case "right":
                dir = "down";
                break;
            case "down":
                dir = "left";
                break;
            case "left":
                dir = "up";
                break;
            default:
                //case "up":
                dir = "right";
                break;
            }

            moveCount = 0;
        }
        switch (dir) {
        case "right":
            setPosition(getRow(), getCol() + 1);
            break;
        case "down":
            setPosition(getRow() + 1, getCol());
            break;
        case "left":
            setPosition(getRow(), getCol() - 1);
            break;
        default:
            //case "up":
            setPosition(getRow() - 1, getCol());
            break;
        }
        moveCount++;
    }
}
