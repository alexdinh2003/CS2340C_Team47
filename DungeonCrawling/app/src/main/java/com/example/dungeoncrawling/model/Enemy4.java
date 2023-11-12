package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class Enemy4 extends Enemy implements EnemySubscriber {
    private String dir = "down";
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
                case "up":
                    dir = "right";
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
            case "up":
                setPosition(getRow() - 1, getCol());
                break;
        }
        moveCount++;
    }

    @Override
    public void update(EnemyPlayerCollision subject) {

    }
}
