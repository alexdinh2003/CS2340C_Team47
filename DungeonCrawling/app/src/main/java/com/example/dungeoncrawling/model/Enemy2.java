package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class Enemy2 extends Enemy implements EnemySubscriber {
    private int moveCount = 0;
    private boolean moveRight = true;
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
        if (moveCount > 4) {
            moveCount = 0;
            moveRight = !moveRight;
        }
        if (moveRight) {
            setPosition(getRow(), getCol() + 1);
        } else {
            setPosition(getRow(), getCol() - 1);

        }
        moveCount += 1;

    }


    @Override
    public void update(EnemyPlayerCollision subject) {

    }
}
