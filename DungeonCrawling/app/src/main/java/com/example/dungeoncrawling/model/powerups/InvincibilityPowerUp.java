package com.example.dungeoncrawling.model.powerups;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.map.MapLayout;

public class InvincibilityPowerUp extends PowerUpDecorator {
    public InvincibilityPowerUp(PowerUp power) {
        super(power);
    }

    @Override
    public void powerUp() {
        System.out.println("Invincible!");
    }

    public void draw(Canvas c) {
        if (wrappee.getSpriteSheet() == null) {
            System.out.println("Sorry, it looks like you never specified a "
                    + "sprite sheet for the powerup.");
            return;
        }
        Sprite sprite = wrappee.getSpriteSheet().getInvinciblePotion();
        sprite.draw(
                c,
                wrappee.getPos()[1] * MapLayout.TILE_WIDTH,
                wrappee.getPos()[0] * MapLayout.TILE_HEIGHT + 256);
    }
}
