package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.map.MapLayout;

public class SpeedPowerUp extends PowerUpDecorator {
    public SpeedPowerUp(PowerUp power) {
        super(power);
    }

    @Override
    public void powerUp() {
        //give the player speed, just have to change it here
    }

    public void draw(Canvas c) {
        if (wrappee.getSpriteSheet() == null) {
            System.out.println("Sorry, it looks like you never specified a "
                    + "sprite sheet for the powerup.");
            return;
        }
        Sprite sprite = wrappee.getSpriteSheet().getTimeStopPotion();
        sprite.draw(
                c,
                wrappee.getPos()[1] * MapLayout.TILE_WIDTH,
                wrappee.getPos()[0] * MapLayout.TILE_HEIGHT + 256);
    }
}
