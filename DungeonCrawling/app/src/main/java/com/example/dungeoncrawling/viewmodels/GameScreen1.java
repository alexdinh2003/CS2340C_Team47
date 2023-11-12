package com.example.dungeoncrawling.viewmodels;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dungeoncrawling.model.Enemy1;
import com.example.dungeoncrawling.model.EnemyPlayerCollision;
import com.example.dungeoncrawling.model.WallCheck;
import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.R;
import com.example.dungeoncrawling.model.Leaderboard;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.ScoreEntry;
import com.example.dungeoncrawling.model.Timer;
import com.example.dungeoncrawling.model.DirectionStrategy;
import com.example.dungeoncrawling.model.Down;
import com.example.dungeoncrawling.model.Left;
import com.example.dungeoncrawling.model.Right;
import com.example.dungeoncrawling.model.Up;
import com.example.dungeoncrawling.model.map.Tilemap;

import java.util.Date;
import com.example.dungeoncrawling.model.FactoryPattern;

public class GameScreen1 extends AppCompatActivity {
    private TextView playerName;
    private TextView difficulty;
    private TextView timerText;
    private ImageView sprite;
    private Timer timer;
    private TextView scoreText;
    private int roomInd;
    private Player player;
    private Enemy1 enemy1;
    private Button left;
    private Button right;
    private Button up;
    private Button down;
    private DirectionStrategy strategy;
    private Down downStrat;
    private Up upStrat;
    private Left leftStrat;
    private Right rightStrat;
    private Tilemap tilemap;
    private SurfaceView surface;
    private SpriteSheet spriteSheet;
    private WallCheck wallCheck;
    private GameMap map;
    private EnemyPlayerCollision enemyPlayerCollision;
    private HP hp;

    /** @noinspection checkstyle:MissingSwitchDefault*/
    /** @noinspection checkstyle:MissingSwitchDefault, checkstyle:MethodLength */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //show top bar (from xml file)
        setContentView(R.layout.game_screen_1);

        roomInd = getIntent().getIntExtra("Room Number", 0);
        spriteSheet = new SpriteSheet(getApplicationContext());

        //draw map and player (access through GameMap map)
        surface = (SurfaceView) findViewById(R.id.surface);
        surface.requestFocus();
        map = new GameMap(surface.getHolder(), spriteSheet, roomInd, getApplicationContext());
        surface.getHolder().addCallback(map);

        //draw enemy - causes blank screen
        //FactoryPattern factory = new FactoryPattern("enemy1", canvas);

        //create all buttons/text fields/image views in top bar
        playerName = findViewById(R.id.playerNameDisplay);
        difficulty = findViewById(R.id.difficultyDisplay);
        sprite = findViewById(R.id.sprite);
        timerText = findViewById(R.id.timerTextView);
        scoreText = findViewById(R.id.scoreTextView);
        player = Player.getInstance();
        playerName.setText(player.getName());
        hp = HP.getInstance();

        //buttons for movement
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);

        //run/restart timer
        timer = new Timer(System.currentTimeMillis(), timerText, scoreText);
        timer.runTimer();

        //instantiate strategies for playerMovement for each button
        downStrat = new Down();
        upStrat = new Up();
        leftStrat = new Left();
        rightStrat = new Right();

        tilemap = new Tilemap(spriteSheet, roomInd);
        wallCheck = new WallCheck(tilemap);
        wallCheck.subscribe(player, player.getRow(), player.getCol());

        // Instantiate Enemy1 before subscribing to enemyPlayerCollision
        //enemy1 = new Enemy1();
        //notify the enemy player collision
        //enemyPlayerCollision.subscribe(enemy1, player.getRow(), player.getCol());

        // Instantiate the HP class with the correct difficulty
        //hp = new HP(this, getDifficulty());

        //make sure health in top bar is in correct size/location
        //move to GameMap class
        switch (hp.getDifficulty()) {
        case 1:
            difficulty.setText("Easy");
            break;
        case 2:
            difficulty.setText("Medium");
            break;
        case 3:
            difficulty.setText("Hard");
            break;
        default:
            System.out.println("Error!");

        }


        //show correct sprite icon in top bar
        switch (player.getSpriteId()) {
        case 0:
            sprite.setImageResource(R.drawable.char1);
            break;
        case 1:
            sprite.setImageResource(R.drawable.char2);
            break;
        case 2:
            sprite.setImageResource(R.drawable.char3);
            break;
        default:
            System.out.println("Error!");
        }

        //player movement with buttons
        left.setOnClickListener(v -> {
            movePlayer("left");
            map.render();
        });
        right.setOnClickListener(v -> {
            movePlayer("right");
            map.render();
        });
        up.setOnClickListener(v -> {
            movePlayer("up");
            map.render();
        });
        down.setOnClickListener(v -> {
            movePlayer("down");
            map.render();
        });

    }

    private void movePlayer(String dir) {
        switch (dir) {
        case "left":
            strategy = leftStrat;
            //change = map.update(leftStrat);
            break;
        case "right":
            strategy = rightStrat;
            //change = map.update(rightStrat);
            break;
        case "up":
            strategy = upStrat;
            //change = map.update(upStrat);
            break;
        case "down":
            strategy = downStrat;
            //change = map.update(downStrat);
            break;
        default:
            strategy = leftStrat;
            //change = map.update(leftStrat);
        }

        int[] newLoc = strategy.move(this.player);
        wallCheck.check(newLoc[0], newLoc[1]);

        if (tilemap.isExit(this.player.getRow(), this.player.getCol())) {
            changeScreen();
        }

        //enemyPlayerCollision.check(newLoc[0], newLoc[1]);


    }

    private void changeScreen() {
        timer.stopTimer();
        Intent nextScreen;
        if (roomInd < 2) {
            nextScreen = new Intent(GameScreen1.this, GameScreen1.class);
            nextScreen.putExtra("Room Number", ++roomInd);
        } else {
            nextScreen = new Intent(GameScreen1.this, GameEnd.class);
            Leaderboard leaderboard = Leaderboard.getInstance();
            ScoreEntry scoreEntry = new ScoreEntry(new Date());
            leaderboard.addScore(scoreEntry);
        }
        startActivity(nextScreen);
        finish();
    }

    private void drawHearts(Canvas canvas) {
        hp.draw(canvas, player.getHealth());
    }
}



