package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class HealthPowerUp extends PowerUpDecorator {
    public HealthPowerUp(PowerUp power) {
        super(power);
    }

    @Override
    public void powerUp() {
        Player p = Player.getInstance();
        HP hp = HP.getInstance();
        p.setHealth(hp.getMaxHearts());
    }

    @Override
    public void draw(Canvas c) {
        if (wrappee.getSpriteSheet() == null) {
            System.out.println("Sorry, it looks like you never specified a "
                    + "sprite sheet for the powerup.");
            return;
        }
        Sprite sprite = wrappee.getSpriteSheet().getHealthPotion();
        sprite.draw(
                c,
                wrappee.getPos()[1] * MapLayout.TILE_WIDTH,
                wrappee.getPos()[0] * MapLayout.TILE_HEIGHT + 256);
    }
}
