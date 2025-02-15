package com.example.dungeoncrawling.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.example.dungeoncrawling.model.powerups.DefaultPowerUp;
import com.example.dungeoncrawling.model.enemies.Enemy;
import com.example.dungeoncrawling.model.enemies.EnemyFactory;
import com.example.dungeoncrawling.model.enemies.EnemyPlayerCollision;
import com.example.dungeoncrawling.model.powerups.HealthPowerUp;
import com.example.dungeoncrawling.model.powerups.InvincibilityPowerUp;
import com.example.dungeoncrawling.model.powerups.PowerUp;
import com.example.dungeoncrawling.model.powerups.PowerUpCheck;
import com.example.dungeoncrawling.model.powerups.SpeedPowerUp;
import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.WallCheck;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.Tilemap;
import com.example.dungeoncrawling.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMap implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private Tilemap tilemap;
    private Paint white;
    private Player player;
    private WallCheck wallCheck;
    private GameLoop gameLoop;
    private static List<Enemy> enemies = new ArrayList<>();
    private Enemy enemy1;
    private Enemy enemy2;
    private HP health;
    private Context context;
    private EnemyPlayerCollision enemyPlayerCollision;
    private PowerUp[] powerUps;
    private PowerUpCheck powerUpCheck;

    private boolean attack = false;
    public GameMap(SurfaceHolder holder, SpriteSheet spriteSheet, int roomInd, Context context) {
        this.holder = holder;
        this.context = context;
        this.tilemap = new Tilemap(spriteSheet, roomInd);
        this.white = new Paint();
        this.white.setColor(-1);

        this.player = Player.getInstance();
        this.player.setSpriteSheet(spriteSheet);
        this.player.setInitalPosition(this.tilemap.getStartPos());

        this.health = HP.getInstance();
        this.health.setSpriteSheet(spriteSheet);

        createPowerUps(roomInd, spriteSheet);

        player.changeSprite();

        createEnemies(roomInd);
        for (Enemy enemy : enemies) {
            enemy.setSpriteSheet(spriteSheet);
        }

        collisionSetUp();

        this.gameLoop = new GameLoop(this);
    }

    public void render() {
        if (player.getHealth() == 0) {
            gameOver();
        }
        Canvas c = this.holder.lockCanvas();
        if (c != null) {
            c.drawRect(new Rect(0, 0, 4000, 1000), white);
            this.tilemap.draw(c);
            this.health.draw(c, player.getHealth());
            List<PowerUp> powerUpsLeft = powerUpCheck.getSubscribers();
            for (PowerUp power : powerUpsLeft) {
                power.draw(c);
            }
            if (attack) {
                player.changeSprite();
            }
            this.player.draw(c);

            for (Enemy enemy : enemies) {
                enemy.draw(c);
            }
            this.holder.unlockCanvasAndPost(c);
        }
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.move();
        }
        if (attack) {
            player.changeSprite();
            attack = false;
        }
        enemyPlayerCollision.check(player.getRow(), player.getCol());
        powerUpCheck.check(player.getPosition());
    }

    public void gameOver() {
        Intent gameOverIntent = new Intent(context, GameEnd.class);
        gameOverIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        gameOverIntent.putExtra("GameOver", true);
        enemyPlayerCollision.removeAll();
        context.startActivity(gameOverIntent);
        this.gameLoop.endGameLoop();
    }

    public void createEnemies(int room) {
        switch (room) {
        case 0:
            enemies.add(EnemyFactory.getEnemy("enemy1", 2, 5));
            enemies.add(EnemyFactory.getEnemy("enemy2", 20, 3));
            //enemy1 = EnemyFactory.getEnemy("enemy1", 2, 5);
            //enemy2 = EnemyFactory.getEnemy("enemy2", 20, 3);
            break;
        case 1:
            enemies.clear();
            enemies.add(EnemyFactory.getEnemy("enemy2", 10, 11));
            enemies.add(EnemyFactory.getEnemy("enemy3", 15, 3));
            //enemy1 = EnemyFactory.getEnemy("enemy2", 10, 11);
            //enemy2 = EnemyFactory.getEnemy("enemy3", 15, 3);
            break;
        case 2:
            enemies.clear();
            enemies.add(EnemyFactory.getEnemy("enemy3", 1, 5));
            enemies.add(EnemyFactory.getEnemy("enemy4", 15, 7));
            //enemy1 = EnemyFactory.getEnemy("enemy3", 1, 5);
            //enemy2 = EnemyFactory.getEnemy("enemy4", 15, 7);
            break;
        default:
            System.out.println("Error");
        }
    }

    public void createPowerUps(int room, SpriteSheet spriteSheet) {
        this.powerUps = new PowerUp[2];

        PowerUp defaultPower = new DefaultPowerUp(spriteSheet, this.tilemap.getPowerUpPos()[0]);
        PowerUp defaultPower2 = new DefaultPowerUp(spriteSheet, this.tilemap.getPowerUpPos()[1]);

        switch (room) {
        case 0:
            powerUps[0] = new HealthPowerUp(defaultPower);
            powerUps[1] = new InvincibilityPowerUp(defaultPower2);
            break;
        case 1:
            powerUps[0] = new InvincibilityPowerUp(defaultPower);
            powerUps[1] = new SpeedPowerUp(defaultPower2);
            break;
        default:
            powerUps[0] = new HealthPowerUp(defaultPower);
            powerUps[1] = new SpeedPowerUp(defaultPower2);
        }

    }

    public void collisionSetUp() {
        this.wallCheck = new WallCheck(this.tilemap);
        this.wallCheck.subscribe(this.player, this.player.getRow(), this.player.getCol());

        this.enemyPlayerCollision = new EnemyPlayerCollision(player.getRow(), player.getCol());
        for (Enemy enemy : enemies) {
            this.enemyPlayerCollision.subscribe(enemy);
        }
        //this.enemyPlayerCollision.subscribe(enemy1);
        //this.enemyPlayerCollision.subscribe(enemy2);

        this.powerUpCheck = PowerUpCheck.getInstance(player.getPosition());
        this.powerUpCheck.unsubscribeAll();
        this.powerUpCheck.subscribe(powerUps[0]);
        this.powerUpCheck.subscribe(powerUps[1]);
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        this.gameLoop.startGameLoop();
        render();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
    public Player getPlayer() {
        return player;
    }

    public Context getContext() {
        return context;
    }

    public void playerAttack(int[] pos) {
        attack = true;

        for (int i = 0; i < enemies.size(); i++) {
            int[] ePos = enemies.get(i).getPosition();
            double dist = Math.sqrt((ePos[1] - pos[1]) * (ePos[1] - pos[1])
                    + (ePos[0] - pos[0]) * (ePos[0] - pos[0]));
            if (dist <= 1) {
                enemies.remove(i);
                player.setScore(player.getScore() + 5);
                i--;
            }
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}