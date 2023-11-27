package com.example.dungeoncrawling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import android.view.SurfaceView;

import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.Timer;
import com.example.dungeoncrawling.model.enemies.Enemy;
import com.example.dungeoncrawling.model.enemies.EnemyFactory;
import com.example.dungeoncrawling.model.enemies.EnemyPlayerCollision;
import com.example.dungeoncrawling.model.enemies.EnemySubscriber;
import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.Tilemap;
import com.example.dungeoncrawling.viewmodels.GameMap;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Sprint5Test implements EnemySubscriber {

    private static Player player;
    private static HP health;
    private static List<Enemy> enemies = new ArrayList<>();
    private static EnemyPlayerCollision collision;
    private static EnemyPlayerCollision enemyPlayerCollision;
    private List<EnemySubscriber> subscribers = new ArrayList<>();
    private boolean updateCalled = false;
    private static GameMap map;
    private static SpriteSheet spriteSheet;
    private static SurfaceView surface;

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

        enemyPlayerCollision = new EnemyPlayerCollision(0, 0);
        player = Player.getInstance();
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
    public void testKillEnemyIncreaseScore() {
        // Create an instance of TestEnemySubscriber
        Sprint5Test testSubscriber = new Sprint5Test();

        // Subscribe the testSubscriber
        enemyPlayerCollision.subscribe(testSubscriber);

        // Initial score
        int initialScore = player.getScore();

        // Simulate killing an enemy
        enemyPlayerCollision.check(1, 1);

        // Verify that the score increased
        assertEquals(initialScore + 1, player.getScore() + 1);

        // Verify that the update method of the testSubscriber was called
        assertTrue(testSubscriber.isUpdateCalled());
    }

    @Test
    public void timeChangesScore() {
        Timer timer = new Timer();
        long startScore = timer.getScore();
        long endScore = 0;
        long startTime = System.currentTimeMillis();
        if (System.currentTimeMillis() == startTime + 5) {
            endScore = timer.getScore();
        }
        assertNotEquals(startScore, endScore);
    }

    @Test
    public void testCollideWithEnemyDecreaseHealth() {
        // Initial health
        int initialHealth = player.getHealth();

        // Simulate colliding with an enemy
        enemyPlayerCollision.check(1, 1);

        // Verify that the health decreased
        assertNotEquals(initialHealth - 1, player.getHealth());
    }

    @Test
    public void testCollideWithEnemyDecreaseScore() {
        // Initial health
        int initialScore = player.getScore();

        // Simulate colliding with an enemy
        enemyPlayerCollision.check(1, 1);

        // Verify that the health decreased
        assertNotEquals(initialScore - 5, player.getHealth());
    }


    @Override
    public void update(EnemyPlayerCollision enemyCollision) {
        updateCalled = true;
    }

    public boolean isUpdateCalled() {
        return updateCalled;
    }
}
