package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class DefaultPowerUp implements PowerUp {
    private SpriteSheet spriteSheet;
    private int[] pos;

    public DefaultPowerUp(SpriteSheet spriteSheet, int[] pos) {
        this.pos = pos;
        this.spriteSheet = spriteSheet;
    }

    @Override
    public void powerUp() {
        Player p = Player.getInstance();
        p.setHealth(p.getHealth() + 2);
    }

    @Override
    public SpriteSheet getSpriteSheet() {
        return this.spriteSheet;
    }

    @Override
    public int[] getPos() {
        return pos;
    }

    @Override
    public void draw(Canvas c) {
        if (this.spriteSheet == null) {
            System.out.println("Sorry, it looks like you never specified a "
                    + "sprite sheet for the powerup.");
            return;
        }
        Sprite sprite = this.spriteSheet.getHealthPotion();
        sprite.draw(
                c,
                this.pos[1] * MapLayout.TILE_WIDTH,
                this.pos[0] * MapLayout.TILE_HEIGHT + 256);
    }
}
