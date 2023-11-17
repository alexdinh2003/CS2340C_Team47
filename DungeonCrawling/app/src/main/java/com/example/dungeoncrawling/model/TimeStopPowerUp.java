package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class TimeStopPowerUp extends PowerUpDecorator {
    public TimeStopPowerUp(PowerUp power, int row, int col) {
        super(power, row, col);
    }

    public TimeStopPowerUp(PowerUp power, SpriteSheet spriteSheet, int row, int col) {
        super(power, spriteSheet, row, col);
    }

    @Override
    public void createSprite() {
        this.sprite = this.spriteSheet.getTimeStopPotion();
    }

    @Override
    public void powerUp() {
        //intefere with GameLoop
    }
}
