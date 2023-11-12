package com.example.dungeoncrawling.model;
import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.map.MapLayout;

public class Enemy1 extends Enemy implements EnemySubscriber {

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
    }

    @Override
    public void update(EnemyPlayerCollision subject) {

    }
}
