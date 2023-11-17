package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class PowerUpDecorator implements PowerUp {

    protected PowerUp wrappee;
    protected Sprite sprite;
    protected SpriteSheet spriteSheet;
    protected int row;
    protected int col;

    public PowerUpDecorator(PowerUp power, int row, int col) {
        this.wrappee = power;
        this.row = row;
        this.col = col;
    }

    public PowerUpDecorator(PowerUp power, SpriteSheet spriteSheet, int row, int col) {
        this.row = row;
        this.col = col;
        this.wrappee = power;
        this.spriteSheet = spriteSheet;
        if (this.spriteSheet != null) {
            createSprite();
        }
    }

    public void createSprite() {
        this.sprite = spriteSheet.getInvinciblePotion();
    }

    @Override
    public void draw(Canvas c) {
        this.wrappee.draw(c);
    }

    @Override
    public void powerUp() {
        this.wrappee.powerUp();
    }
}
