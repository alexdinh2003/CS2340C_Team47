package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class Enemy3 extends Enemy implements EnemySubscriber {
    private int moveCount = 0;
    private boolean moveRight = true;
    public Enemy3(int row, int col, SpriteSheet spriteSheet) {
        super(row, col, spriteSheet);
    }

    public Enemy3(int row, int col) {
        super(row, col);
    }

    public void createSprite() {
        this.sprite = this.getSpriteSheet().getEnemy(2);
    }

    public void move() {
        //change position in some way every 1/2 sec
        if (moveCount > 3) {
            moveCount = 0;
            moveRight = !moveRight;
        }
        if (moveRight) {
            setPosition(getRow() + 1, getCol() + 1);
        } else {
            setPosition(getRow() - 1, getCol() - 1);
        }
        moveCount += 1;
    }

    @Override
    public void update(EnemyPlayerCollision subject) {

    }
}
