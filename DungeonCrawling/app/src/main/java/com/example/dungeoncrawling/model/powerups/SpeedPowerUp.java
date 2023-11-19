package com.example.dungeoncrawling.model.powerups;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.map.MapLayout;

public class SpeedPowerUp extends PowerUpDecorator {

    private long startTime;
    private boolean active = false;
    private static final int SPEED = 2;
    private Player p;
    private static final int EFFECT_TIME = 10 * 1000;

    public SpeedPowerUp(PowerUp power) {
        super(power);
        p = Player.getInstance();
    }

    @Override
    public void powerUp() {
        // if the power up has just activated, store start time & set player speed to 3
        if (!this.active) {
            this.active = true;
            this.startTime = System.currentTimeMillis();
            p.setSpeed(SPEED);
            return;
        }
        // if time of effect has ended, set active = false
        if (System.currentTimeMillis() - startTime >= EFFECT_TIME) {
            this.active = false;
            p.setSpeed(1);
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void draw(Canvas c) {
        if (wrappee.getSpriteSheet() == null) {
            System.out.println("Sorry, it looks like you never specified a "
                    + "sprite sheet for the powerup.");
            return;
        }
        if (active) {
            return;
        }
        Sprite sprite = wrappee.getSpriteSheet().getTimeStopPotion();
        sprite.draw(
                c,
                wrappee.getPos()[1] * MapLayout.TILE_WIDTH,
                wrappee.getPos()[0] * MapLayout.TILE_HEIGHT + 256);
    }
}
