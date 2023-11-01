package com.example.dungeoncrawling.viewmodels;

import android.annotation.SuppressLint;
import android.content.Intent;


import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dungeoncrawling.model.graphics.SpriteSheet;
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
    private int roomInd;
    private Player player;
    private Button left;
    private Button right;
    private Button up;
    private Button down;
    private Down downStrat;
    private Up upStrat;
    private Left leftStrat;
    private Right rightStrat;
    private SurfaceView surface;
    private SpriteSheet spriteSheet;
    private GameMap map;

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
        map = new GameMap(surface.getHolder(), spriteSheet, roomInd);
        surface.getHolder().addCallback(map);

        //create all buttons/text fields/image views in top bar
        playerName = findViewById(R.id.playerNameDisplay);
        difficulty = findViewById(R.id.difficultyDisplay);
        sprite = findViewById(R.id.sprite);
        health = findViewById(R.id.health);
        timerText = findViewById(R.id.timerTextView);
        scoreText = findViewById(R.id.scoreTextView);
        player = Player.getInstance();
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
        boolean change = false;

        switch (dir) {
        case "left":
            change = map.update(leftStrat);
            break;
        case "right":
            change = map.update(rightStrat);
            break;
        case "up":
            change = map.update(upStrat);
            break;
        case "down":
            change = map.update(downStrat);
            break;
        default:
            change = map.update(leftStrat);
        }

        if (change) {
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



