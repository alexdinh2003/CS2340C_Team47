package com.example.dungeoncrawling;

import static org.junit.Assert.assertEquals;

import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.enemies.Enemy;
import com.example.dungeoncrawling.model.enemies.EnemyFactory;
import com.example.dungeoncrawling.model.enemies.EnemyPlayerCollision;
import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.powerups.DefaultPowerUp;
import com.example.dungeoncrawling.model.powerups.HealthPowerUp;
import com.example.dungeoncrawling.model.powerups.InvincibilityPowerUp;
import com.example.dungeoncrawling.model.powerups.PowerUp;
import com.example.dungeoncrawling.model.powerups.PowerUpCheck;
import com.example.dungeoncrawling.model.powerups.SpeedPowerUp;


import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PowerUpTests {
    private static Player player;
    private static HP health;
    private static PowerUp[] powerUps;
    private static PowerUpCheck powerUpCheck;
    private static Enemy enemy;
    private static EnemyPlayerCollision collision;

    @BeforeClass
    public static void setUp() {
        //player creation
        player = Player.getInstance("me", 10,1,1);
        player.setHealth(5);
        //determines how much health will be lost
        health = HP.getInstance(1);
        health.setDifficulty(1);

        //enemy and enemy player collision object
        enemy = EnemyFactory.getEnemy("enemy1", 4, 1);

        collision = new EnemyPlayerCollision(player.getRow(), player.getCol());
        collision.subscribe(enemy);

        powerUps = new PowerUp[3];
        PowerUp defaultPower = new DefaultPowerUp(new int[] {2,1});
        PowerUp defaultPower2 = new DefaultPowerUp(new int[] {3,1});
        PowerUp defaultPower3 = new DefaultPowerUp(new int[] {5,1});
        powerUps[0] = new SpeedPowerUp(defaultPower);
        powerUps[1] = new InvincibilityPowerUp(defaultPower2);
        powerUps[2] = new HealthPowerUp(defaultPower3);

        powerUpCheck = PowerUpCheck.getInstance(player.getPosition());
        for (int i = 0; i < 3; i++) {
            powerUpCheck.subscribe(powerUps[i]);
        }
    }

    @Test
    public void speedPowerUp() {
        player.setPosition(2,1);
        powerUpCheck.check(player.getPosition());
        assertEquals(2, player.getSpeed());
    }

    @Test
    public void invincibilityPowerUp() {
        player.setPosition(3,1);
        player.setHealth(5);
        powerUpCheck.check(player.getPosition());
        player.setPosition(4,1);
        collision.check(player.getRow(), player.getCol());
        powerUpCheck.check(player.getPosition());
        assertEquals(5, player.getHealth());
    }

    @Test
    public void healthPowerUp() {
        player.setPosition(5,1);
        powerUpCheck.check(player.getPosition());
        assertEquals(10, player.getHealth());
    }
}
