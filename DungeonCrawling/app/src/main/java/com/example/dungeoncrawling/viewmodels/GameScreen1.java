package com.example.dungeoncrawling.viewmodels;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.SurfaceHolder.Callback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.model.map.MapLayout;
import com.example.dungeoncrawling.model.map.Tilemap;
import com.example.dungeoncrawling.R;
import com.example.dungeoncrawling.model.Leaderboard;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.ScoreEntry;
import com.example.dungeoncrawling.model.Timer;
import java.util.Date;

public class GameScreen1 extends AppCompatActivity {
    private Button next;
    //Temp button
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

    private Player player1;

    // Define the boundaries of the game world
    private int maxX = MapLayout.NUM_COLS;
    private int maxY = MapLayout.NUM_ROWS;

    // Define exitX and exitY for room transitions
    private int exitX; // Set the actual X-coordinate of the exit
    private int exitY; // Set the actual Y-coordinate of the exit

    private Button left;
    private Button right;
    private Button up;
    private Button down;

    /** @noinspection checkstyle:MissingSwitchDefault*/
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen_1);

        SurfaceView surface = (SurfaceView) findViewById(R.id.surface);
        surface.getHolder().addCallback(new Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Do some drawing when surface is ready
                Canvas canvas = holder.lockCanvas();
                SpriteSheet spriteSheet = new SpriteSheet(getApplicationContext());
                tilemap = new Tilemap(spriteSheet, roomInd);
                Paint paint = new Paint();
                paint.setColor(-1);
                canvas.drawRect(new Rect(0, 0, 4000, 1000), paint);

                //draw tile map
                tilemap.draw(canvas);

                // character's position
                characterX = 0;
                characterY = 0;

                //next position of character
                int nextCharacterX = characterX + characterSpeed;
                int nextCharacterY = characterY;

                //check for collisions with wall
                int tileX = nextCharacterX / tileSize;
                int tileY = nextCharacterY / tileSize;

                //unlock canvas and post drawing
                int[] startPos = tilemap.getStartPos();
                player.setSpriteSheet(spriteSheet);
                player.setPositionArr(startPos);
                player.draw(canvas);

                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });


        playerName = findViewById(R.id.playerNameDisplay);
        difficulty = findViewById(R.id.difficultyDisplay);
        sprite = findViewById(R.id.sprite);
        health = findViewById(R.id.health);
        timerText = findViewById(R.id.timerTextView);
        scoreText = findViewById(R.id.scoreTextView);
        next  = findViewById(R.id.nextScreenButton);
        player = Player.getInstance();
        roomInd = getIntent().getIntExtra("Room Number", 0);
        playerName.setText(player.getName());

        timer = new Timer(System.currentTimeMillis(), timerText, scoreText);
        timer.runTimer();

        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);

        if (roomInd == 2) {
            next.setText("Exit");
        }

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)
                health.getLayoutParams();

        switch (player.getHealth()) {
        case 5:
            difficulty.setText("Easy");
            params.horizontalBias = 0.73f;
            health.setLayoutParams(params);
            health.setImageResource(R.drawable.five_hearts);
            health.getLayoutParams().width = 450;
            break;
        case 4:
            difficulty.setText("Medium");
            params.horizontalBias = 0.65f;
            health.setLayoutParams(params);
            health.setImageResource(R.drawable.four_hearts);
            health.getLayoutParams().width = 350;
            break;
        case 3:
            difficulty.setText("Hard");
            params.horizontalBias = 0.77f;
            health.setLayoutParams(params);
            health.setImageResource(R.drawable.three_hearts);
            health.getLayoutParams().width = 250;
            break;
        default:
            System.out.println("Error!");

        }

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

        left.setOnClickListener(v -> {

            Canvas canvas = surface.getHolder().lockCanvas();
            SpriteSheet spriteSheet = new SpriteSheet(getApplicationContext());
            tilemap = new Tilemap(spriteSheet, roomInd);
            Paint paint = new Paint();
            paint.setColor(-1);
            canvas.drawRect(new Rect(0, 0, 4000, 1000), paint);

            //draw tile map
            tilemap.draw(canvas);

            //unlock canvas and post drawing
            int[] newPos = new int[] {player.getRow(), player.getCol() - 1};
            player.setSpriteSheet(spriteSheet);
            player.setPositionArr(newPos);
            player.draw(canvas);

            surface.getHolder().unlockCanvasAndPost(canvas);

        });

        right.setOnClickListener(v -> {

            Canvas canvas = surface.getHolder().lockCanvas();
            SpriteSheet spriteSheet = new SpriteSheet(getApplicationContext());
            tilemap = new Tilemap(spriteSheet, roomInd);
            Paint paint = new Paint();
            paint.setColor(-1);
            canvas.drawRect(new Rect(0, 0, 4000, 1000), paint);

            //draw tile map
            tilemap.draw(canvas);

            //unlock canvas and post drawing
            int[] newPos = new int[] {player.getRow(), player.getCol() + 1};
            player.setSpriteSheet(spriteSheet);
            player.setPositionArr(newPos);
            player.draw(canvas);

            surface.getHolder().unlockCanvasAndPost(canvas);

        });

        up.setOnClickListener(v -> {

            Canvas canvas = surface.getHolder().lockCanvas();
            SpriteSheet spriteSheet = new SpriteSheet(getApplicationContext());
            tilemap = new Tilemap(spriteSheet, roomInd);
            Paint paint = new Paint();
            paint.setColor(-1);
            canvas.drawRect(new Rect(0, 0, 4000, 1000), paint);

            //draw tile map
            tilemap.draw(canvas);

            //unlock canvas and post drawing
            int[] newPos = new int[] {player.getRow() - 1, player.getCol()};
            player.setSpriteSheet(spriteSheet);
            player.setPositionArr(newPos);
            player.draw(canvas);

            surface.getHolder().unlockCanvasAndPost(canvas);

        });

        down.setOnClickListener(v -> {

            Canvas canvas = surface.getHolder().lockCanvas();
            SpriteSheet spriteSheet = new SpriteSheet(getApplicationContext());
            tilemap = new Tilemap(spriteSheet, roomInd);
            Paint paint = new Paint();
            paint.setColor(-1);
            canvas.drawRect(new Rect(0, 0, 4000, 1000), paint);

            //draw tile map
            tilemap.draw(canvas);

            //unlock canvas and post drawing
            int[] newPos = new int[] {player.getRow() + 1, player.getCol()};
            player.setSpriteSheet(spriteSheet);
            player.setPositionArr(newPos);
            player.draw(canvas);

            surface.getHolder().unlockCanvasAndPost(canvas);

        });
    }
}



