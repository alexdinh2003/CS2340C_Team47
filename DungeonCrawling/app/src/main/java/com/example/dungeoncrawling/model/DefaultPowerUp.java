package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public class DefaultPowerUp implements PowerUp {

    private Sprite sprite;
    private SpriteSheet spriteSheet;
    private int row;
    private int col;

    public DefaultPowerUp(SpriteSheet spriteSheet, int row, int col) {
        this.row = row;
        this.col = col;
        this.spriteSheet = spriteSheet;
        if (this.spriteSheet != null) {
            createSprite();
        }
    }

    private void createSprite() {
        this.sprite = spriteSheet.getHealthPotion();
    }

    @Override
    public void draw(Canvas c) {
        if (this.spriteSheet == null) {
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
        Player p = Player.getInstance();
        p.setHealth(p.getHealth() + 2);
    }
}
