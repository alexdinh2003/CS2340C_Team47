package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class InvincibilityPowerUp extends PowerUpDecorator {
    public InvincibilityPowerUp(PowerUp power, int row, int col) {
        super(power, row, col);
    }

    public InvincibilityPowerUp(PowerUp power, SpriteSheet spriteSheet, int row, int col) {
        super(power, spriteSheet, row, col);
    }

    @Override
    public void createSprite() {
        this.sprite = this.spriteSheet.getInvinciblePotion();
    }

    @Override
    public void powerUp() {
        //interfere with EnemyPlayerCollision
    }
}
