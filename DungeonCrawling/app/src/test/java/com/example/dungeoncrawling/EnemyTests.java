package com.example.dungeoncrawling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.example.dungeoncrawling.model.enemies.Enemy;
import com.example.dungeoncrawling.model.enemies.EnemyFactory;
import com.example.dungeoncrawling.model.enemies.EnemyPlayerCollision;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.graphics.HP;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EnemyTests {

    private static Player player;
    private static HP health;
    private static List<Enemy> enemies = new ArrayList<>();
    private static EnemyPlayerCollision collision;

    @BeforeClass
    public static void setUp() {
        //player creation
        player = Player.getInstance("me", 10,1,1);
        //determines how much health will be lost
        health = HP.getInstance(1);

        //enemy and enemy player collision object
        enemies.add(EnemyFactory.getEnemy("enemy1", 2, 1));
        enemies.add(EnemyFactory.getEnemy("enemy2", 2, 2));
        enemies.add(EnemyFactory.getEnemy("enemy3", 3, 2));
        enemies.add(EnemyFactory.getEnemy("enemy4", 3, 3));

        collision = new EnemyPlayerCollision(player.getRow(), player.getCol());
        collision.subscribe(enemies.get(0));
        collision.subscribe(enemies.get(1));
        collision.subscribe(enemies.get(2));
        collision.subscribe(enemies.get(3));
    }

    @Test
    public void enemyPlayerCollision() {
        player.setHealth(10);
        health.setDifficulty(1);
        player.setPosition(2,1);
        collision.check(player.getRow(), player.getCol());
        assertEquals(9, player.getHealth());
    }

    @Test
    public void difficultyChanges() {
        player.setHealth(10);
        health.setDifficulty(2);
        player.setPosition(2,2);
        collision.check(player.getRow(), player.getCol());
        assertEquals(8, player.getHealth());

        health.setDifficulty(3);
        player.setPosition(3,2);
        collision.check(player.getRow(), player.getCol());
        assertEquals(5, player.getHealth());
    }
}
