package com.example.dungeoncrawling.viewmodels;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeoncrawling.model.WallCheck;
import com.example.dungeoncrawling.model.graphics.HP;
import com.example.dungeoncrawling.model.graphics.SpriteSheet;
import com.example.dungeoncrawling.R;
import com.example.dungeoncrawling.model.Leaderboard;
import com.example.dungeoncrawling.model.Player;
import com.example.dungeoncrawling.model.ScoreEntry;
import com.example.dungeoncrawling.model.Timer;
import com.example.dungeoncrawling.model.playermove.DirectionStrategy;
import com.example.dungeoncrawling.model.playermove.Down;
import com.example.dungeoncrawling.model.playermove.Left;
import com.example.dungeoncrawling.model.playermove.Right;
import com.example.dungeoncrawling.model.playermove.Up;
import com.example.dungeoncrawling.model.map.Tilemap;
import com.example.dungeoncrawling.model.powerups.PowerUpCheck;

import java.util.Date;

public class GameScreen1 extends AppCompatActivity {
    private TextView playerName;
    private TextView difficulty;
    private TextView timerText;
    private ImageView sprite;
    private Timer timer;
    private TextView scoreText;
    private int roomInd;
    private Player player;
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
    private HP hp;
    private PowerUpCheck powerUpCheck;
    private MediaPlayer player1;
  
    private Button stopMusic;
    private Button startMusic;
    private Button attack;


    /** @noinspection checkstyle:MissingSwitchDefault*/
    /** @noinspection checkstyle:MissingSwitchDefault, checkstyle:MethodLength */
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
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

        //button for attack
        attack = findViewById(R.id.attack);

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

        powerUpCheck = PowerUpCheck.getInstance(player.getPosition());


        //make sure difficulty is correctly displayed
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

        //player attack
        attack.setOnClickListener(v -> {
            int[] pos = player.getPosition();
            map.playerAttack(pos);
        });

        play();
    }

    private void movePlayer(String dir) {
        switch (dir) {
        case "left":
            strategy = leftStrat;
            break;
        case "right":
            strategy = rightStrat;
            break;
        case "up":
            strategy = upStrat;
            break;
        case "down":
            strategy = downStrat;
            break;
        default:
            strategy = leftStrat;
        }

        for (int i = 0; i < player.getSpeed(); i++) {
            int[] newLoc = strategy.move(this.player);
            wallCheck.check(newLoc[0], newLoc[1]);
        }

        powerUpCheck.check(player.getPosition());

        if (tilemap.isExit(this.player.getRow(), this.player.getCol())) {
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

    public void play() {
        if (player1 == null) {
            player1 = MediaPlayer.create(this, R.raw.song2);
            player1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player1.start();
    }

    public void stop() {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player1 != null) {
            player1.release();
            player1 = null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT);
        }
    }
}



