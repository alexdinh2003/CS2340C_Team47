package com.example.dungeoncrawling.model;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.graphics.Sprite;
public class Enemy1 extends Enemy {
    int row;
    int col;
    SpriteSheet spriteSheet;
    Sprite sprite;
    private Enemy1 (int row, int col, SpriteSheet spriteSheet) {
        this.row = row;
        this.col = col;
        this.spriteSheet = spriteSheet;
        createSprite();
    }

    public void createSprite() {
        //add specific sprite id for this enemy
        this.sprite = this.spriteSheet.getEnemy();
    }


}
