package com.example.dungeoncrawling.model;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.graphics.Sprite;
public class Enemy1 extends Enemy {
    private int row;
    private int col;
    private SpriteSheet spriteSheet;
    private Sprite sprite;
    private int spriteId;
    private Enemy1(int row, int col, SpriteSheet spriteSheet) {
        this.row = row;
        this.col = col;
        this.spriteSheet = spriteSheet;
        createSprite();
    }

    public void createSprite() {
        //add specific sprite id for this enemy
        this.sprite = this.spriteSheet.getEnemy(this.spriteId);
    }


}
