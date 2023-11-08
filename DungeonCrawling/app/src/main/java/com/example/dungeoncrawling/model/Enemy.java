package com.example.dungeoncrawling.model;

import android.graphics.Canvas;

import com.example.dungeoncrawling.model.graphics.Sprite;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;

public interface Enemy {

        public SpriteSheet getSpriteSheet();

        public void setSpriteSheet(SpriteSheet spriteSheet);

        public int[] getPosition();

        public void setPosition(int row, int col);

        public void setInitalPosition(int[] rowCol);

        public void createSprite();

        public int getRow();

        public int getCol();

        //add method for movement

        public void draw(Canvas canvas);
}
