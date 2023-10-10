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

import com.example.dungeoncrawling.graphics.SpriteSheet;
import com.example.dungeoncrawling.map.Tilemap;
import com.example.dungeoncrawling.R;
import com.example.dungeoncrawling.model.Leaderboard;
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
    private int difficultyNum;
    private String playerNameStr;
    private int spriteNum;
    private Timer timer;
    private TextView scoreText;
    private Tilemap tilemap;
    private int roomInd;

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
                canvas.drawRect(new Rect(0, 0, 4000, 1000),paint);
                tilemap.draw(canvas);
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

        difficultyNum = getIntent().getIntExtra("difficulty", 1);
        playerNameStr = getIntent().getStringExtra("playerName");
        spriteNum = getIntent().getIntExtra("spriteNum", 1);
        roomInd = getIntent().getIntExtra("Room Number", 0);

        playerName.setText(playerNameStr);

        timer = new Timer(System.currentTimeMillis(), timerText, scoreText);
        timer.runTimer();

        if (roomInd == 2) {
            next.setText("Exit");
        }
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)
                health.getLayoutParams();

        switch (difficultyNum) {
        case 1:
            difficulty.setText("Easy");
            params.horizontalBias = 0.73f;
            health.setLayoutParams(params);
            health.setImageResource(R.drawable.five_hearts);
            health.getLayoutParams().width = 450;
            break;
        case 2:
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

        switch (spriteNum) {
        case 1:
            sprite.setImageResource(R.drawable.panda_sprite);
            break;
        case 2:
            sprite.setImageResource(R.drawable.sheep_sprite);
            break;
        case 3:
            sprite.setImageResource(R.drawable.monkey_sprite);
            break;
        default:
            System.out.println("Error!");
        }


        exitGame.setOnClickListener(v -> {
            int score = timer.getScore();

        next.setOnClickListener(v -> {

            timer.stopTimer();
            Intent nextScreen;
            if (roomInd < 2) {
                nextScreen = new Intent(GameScreen1.this, GameScreen1.class);
                nextScreen.putExtra("Room Number", ++roomInd);
                nextScreen.putExtra("difficulty", difficultyNum);
                nextScreen.putExtra("playerName", playerNameStr);
                nextScreen.putExtra("spriteNum", spriteNum);
            } else {
                nextScreen = new Intent(GameScreen1.this, GameEnd.class);
            }
          
            Leaderboard leaderboard = Leaderboard.getInstance();

            int playerScore = score;
            ScoreEntry scoreEntry = new ScoreEntry(playerNameStr, playerScore, new Date());
            leaderboard.addScore(scoreEntry);
            
            startActivity(endScreen);



            int playerScore = timer.getScore();
            ScoreEntry scoreEntry = new ScoreEntry(playerNameStr, playerScore, new Date());
            leaderboard.addScore(scoreEntry);
            startActivity(nextScreen);


        timer = (Timer) getIntent().getSerializableExtra("timer");

        next.setOnClickListener(v -> {
            onPause();
            Intent gameScreen2 = new Intent(GameScreen1.this, GameScreen2.class);
            gameScreen2.putExtra("playerName", playerNameStr);
            //gameScreen2.putExtra("playerScore", timer.getScore());

            startActivity(gameScreen2);
            finish();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause the timer when the activity is paused
        if (timer != null) {
            timer.stopTimer();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Resume the timer when the activity is resumed
        if (timer != null) {
            timer.runTimer();
        }
    }
}


