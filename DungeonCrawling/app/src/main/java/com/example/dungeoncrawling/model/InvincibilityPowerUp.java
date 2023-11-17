package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class InvincibilityPowerUp extends PowerUpDecorator {
    public InvincibilityPowerUp(PowerUp power) {
        super(power);
    }

    public InvincibilityPowerUp(PowerUp power, SpriteSheet spriteSheet, int row, int col) {
        super(power, spriteSheet, row, col);
        if (this.spriteSheet != null) {
            createSprite();
        }
    }

    private void createSprite() {
        this.sprite = this.spriteSheet.getInvinciblePotion();
    }

    @Override
    public void draw(Canvas c) {
        if (spriteSheet == null) {
            System.out.println("Sorry, it looks like you never specified a "
                    + "sprite sheet for the powerup.");
            return;
        }

        sprite.draw(
                c,
                this.col * MapLayout.TILE_WIDTH,
                this.row * MapLayout.TILE_HEIGHT + 256);
    }

    @Override
    public void powerUp() {
        //interfere with EnemyPlayerCollision
    }
}
