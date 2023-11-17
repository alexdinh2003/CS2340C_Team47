package com.example.dungeoncrawling.model.powerups;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;

public class PowerUpDecorator implements PowerUp {
    protected PowerUp wrappee;

    public PowerUpDecorator(PowerUp power) {
        this.wrappee = power;
    }

    @Override
    public void powerUp() {
        this.wrappee.powerUp();
    }

    @Override
    public SpriteSheet getSpriteSheet() {
        return this.wrappee.getSpriteSheet();
    }

    @Override
    public int[] getPos() {
        return this.wrappee.getPos();
    }

    @Override
    public void draw(Canvas c) {
        this.wrappee.draw(c);
    }
}
