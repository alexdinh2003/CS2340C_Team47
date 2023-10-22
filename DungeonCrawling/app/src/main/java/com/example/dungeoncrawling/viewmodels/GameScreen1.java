package com.example.dungeoncrawling.viewmodels;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;

import android.os.Bundle;

import android.os.Handler;
import android.view.KeyEvent;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.SurfaceHolder.Callback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dungeoncrawling.model.DirectionStrategy;
import com.example.dungeoncrawling.model.WallCheck;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.Tilemap;
import com.example.dungeoncrawling.R;
import com.example.dungeoncrawling.model.Leaderboard;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.ScoreEntry;
import com.example.dungeoncrawling.model.Timer;
import com.example.dungeoncrawling.model.Down;
import com.example.dungeoncrawling.model.Left;
import com.example.dungeoncrawling.model.Right;
import com.example.dungeoncrawling.model.Up;
import java.util.Date;

public class GameScreen1 extends AppCompatActivity {
    private TextView playerName;
    private TextView difficulty;
    private TextView timerText;
    private ImageView sprite;
    private ImageView health;
    private Timer timer;
    private TextView scoreText;
    private Tilemap tilemap;
    private int roomInd;
    private Player player;

    private int characterX;
    private int characterY;
    private int characterSpeed = 5;
    private int tileSize = MapLayout.TILE_WIDTH;

    // Define the boundaries of the game world
    private int maxX = MapLayout.NUM_COLS;
    private int maxY = MapLayout.NUM_ROWS;



    private Button left;
    private Button right;
    private Button up;
    private Button down;


    private Handler handler;

    private DirectionStrategy strategy;
    private Down downStrat;
    private Up upStrat;
    private Left leftStrat;
    private Right rightStrat;
    private SurfaceView surface;
    private SpriteSheet spriteSheet;
    private Paint white;
    private WallCheck wallCollision;

    /** @noinspection checkstyle:MissingSwitchDefault*/
    /** @noinspection checkstyle:MissingSwitchDefault, checkstyle:MethodLength */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //show top bar (from xml file)
        setContentView(R.layout.game_screen_1);

        //draw map with player on top (with a canvas)
        surface = (SurfaceView) findViewById(R.id.surface);
        surface.requestFocus();
        surface.getHolder().addCallback(new Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Do some drawing when surface is ready
                Canvas canvas = holder.lockCanvas();
                spriteSheet = new SpriteSheet(getApplicationContext());
                tilemap = new Tilemap(spriteSheet, roomInd);
                white = new Paint();
                white.setColor(-1);
                canvas.drawRect(new Rect(0, 0, 4000, 1000), white);

                //draw tile map
                tilemap.draw(canvas);

                //draw player at correct startPosition for this map
                int[] startPos = tilemap.getStartPos();
                player.setSpriteSheet(spriteSheet);
                player.setInitalPosition(startPos);

                //create wallCheck object to check for wall collisions
                wallCollision = new WallCheck(tilemap);
                wallCollision.subscribe(player, player.getRow(), player.getCol());

                player.draw(canvas);

                //unlock canvas and post drawing
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });

        //create all buttons/text fields/image views in top bar
        playerName = findViewById(R.id.playerNameDisplay);
        difficulty = findViewById(R.id.difficultyDisplay);
        sprite = findViewById(R.id.sprite);
        health = findViewById(R.id.health);
        timerText = findViewById(R.id.timerTextView);
        scoreText = findViewById(R.id.scoreTextView);
        player = Player.getInstance();
        roomInd = getIntent().getIntExtra("Room Number", 0);
        playerName.setText(player.getName());
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

        //make sure health in top bar is in correct size/location
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)
                health.getLayoutParams();
        switch (player.getHealth()) {
        case 5:
            difficulty.setText("Easy");
            params.horizontalBias = 0.33f;
            health.setLayoutParams(params);
            health.setImageResource(R.drawable.five_hearts);
            health.getLayoutParams().width = 300;
            break;
        case 4:
            difficulty.setText("Medium");
            params.horizontalBias = 0.3f;
            health.setLayoutParams(params);
            health.setImageResource(R.drawable.four_hearts);
            health.getLayoutParams().width = 250;
            break;
        case 3:
            difficulty.setText("Hard");
            params.horizontalBias = 0.4f;
            health.setLayoutParams(params);
            health.setImageResource(R.drawable.three_hearts);
            health.getLayoutParams().width = 200;
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
        // using whatever new actionListener we have:
        // move to next screen by checking if player is on exitTile that gives isExit() == T
        // if so, do the nextScreen stuff below
        next.setOnClickListener(v -> {
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
        });

        // Create a Handler object.
        handler = new Handler();

        left.setOnClickListener(v -> {
            Canvas canvas = surface.getHolder().lockCanvas();
            movePlayer(canvas, "left");
        });
        right.setOnClickListener(v -> {
            Canvas canvas = surface.getHolder().lockCanvas();
            movePlayer(canvas, "right");
        });
        up.setOnClickListener(v -> {
            Canvas canvas = surface.getHolder().lockCanvas();
            movePlayer(canvas, "up");
        });
        down.setOnClickListener(v -> {
            Canvas canvas = surface.getHolder().lockCanvas();
            movePlayer(canvas, "down");
        });

    }
  
    private void setDirStrat(DirectionStrategy newStrategy) {
        this.strategy = newStrategy;
    }

    private void movePlayer(Canvas canvas, String dir) {
        //draw white background of stats bar
        canvas.drawRect(new Rect(0, 0, 4000, 1000), white);
        //draw tile map
        tilemap.draw(canvas);

        switch (dir) {
        case "left":
            this.strategy = leftStrat;
            break;
        case "right":
            this.strategy = rightStrat;
            break;
        case "up":
            this.strategy = upStrat;
            break;
        case "down":
            this.strategy = downStrat;
            break;
        default:
            this.strategy = leftStrat;
        }

        int[] newLoc = strategy.move(player);
        wallCollision.check(newLoc[0], newLoc[1]);
        player.draw(canvas);
        surface.getHolder().unlockCanvasAndPost(canvas);
        if (tilemap.isExit(player.getRow(), player.getCol())) {
            changeScreen();
        }
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

}



