package com.example.dungeoncrawling;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.dungeoncrawling.model.enemies.Enemy;
import com.example.dungeoncrawling.model.enemies.EnemyFactory;
import com.example.dungeoncrawling.viewmodels.GameMap;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.graphics.Sprite;
import android.graphics.Rect;
import org.junit.Before;
import org.junit.Test;

public class PlayerAttackTest {

    @Test
    public void playerSpriteSettersGetters() {
        Player p = new Player();
        Sprite sp = new Sprite(null, new Rect(1, 2, 3, 4));
        Sprite sw = new Sprite(null, new Rect(11, 22, 33, 44));
        p.setSprite(sp);
        p.setSword(sw);

        assertEquals(sw, p.getSword());
        assertEquals(sp, p.getSprite());
    }

    @Test
    public void playerSpriteChange() {
        Player p = new Player();
        Sprite sp = new Sprite(null, new Rect(1, 2, 3, 4));
        Sprite sw = new Sprite(null, new Rect(11, 22, 33, 44));
        p.setSprite(sp);
        p.setSword(sw);
        p.changeSprite();

        assertEquals(sp, p.getSword());
        assertEquals(sw, p.getSprite());
    }


}
