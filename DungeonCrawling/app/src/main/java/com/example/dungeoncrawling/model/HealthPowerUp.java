package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class HealthPowerUp extends PowerUpDecorator {
    public HealthPowerUp(PowerUp power, int row, int col) {
        super(power, row, col);
    }

    public HealthPowerUp(PowerUp power, SpriteSheet spriteSheet, int row, int col) {
        super(power, spriteSheet, row, col);
    }

    @Override
    public void createSprite() {
        this.sprite = this.spriteSheet.getHealthPotion();
    }

    @Override
    public void powerUp() {
        Player p = Player.getInstance();
        HP hp = HP.getInstance();
        p.setHealth(hp.getMaxHearts());
    }
}
