package com.example.dungeoncrawling.model.powerups;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.map.MapLayout;

public class InvincibilityPowerUp extends PowerUpDecorator {
    private long startTime;
    private boolean active = false;
    private int health;
    private Player p;
    private static final int EFFECT_TIME = 30 * 1000;
    public InvincibilityPowerUp(PowerUp power) {
        super(power);
        p = Player.getInstance();
    }

    @Override
    public void powerUp() {
        // if the power up has just activated, store start time & current health
        if (!this.active) {
            this.active = true;
            this.startTime = System.currentTimeMillis();
            this.health = p.getHealth();
            return;
        }
        // if time of effect has ended, set active = false
        if (System.currentTimeMillis() - startTime >= EFFECT_TIME) {
            this.active = false;
            return;
        }

        // if player health has decreased since power up activation, reset health to
        // health at time of activation
        if (p.getHealth() < health) {
            p.setHealth(health);
        }
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
        Sprite sprite = wrappee.getSpriteSheet().getInvinciblePotion();
        sprite.draw(
                c,
                wrappee.getPos()[1] * MapLayout.TILE_WIDTH,
                wrappee.getPos()[0] * MapLayout.TILE_HEIGHT + 256);
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
