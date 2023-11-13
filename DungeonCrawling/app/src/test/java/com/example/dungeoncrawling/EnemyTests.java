package com.example.dungeoncrawling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.dungeoncrawling.model.Enemy;
import com.example.dungeoncrawling.model.EnemyFactory;
import com.example.dungeoncrawling.model.EnemyPlayerCollision;
import com.example.dungeoncrawling.model.Player;

import org.junit.Test;

public class EnemyTests {

    @Test
    public void enemyPlayerCollision() {
        Player player = Player.getInstance("me", 5,1,1);
        Enemy enemy1 = EnemyFactory.getEnemy("enemy1", 1, 0);
        EnemyPlayerCollision collision = new EnemyPlayerCollision(player.getRow(), player.getCol());
        collision.subscribe(enemy1);
        player.

    }
}
