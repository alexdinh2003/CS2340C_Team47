package com.example.dungeoncrawling;

<<<<<<< HEAD
import java.util.TimerTask;

=======
import android.annotation.SuppressLint;
>>>>>>> 343af55c1d72e0a270b7568027f2b58b3d9e7315
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;





public class GameScreen1 extends AppCompatActivity {
    private Button exitGame;
    private Button resetGame;
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

    /** @noinspection checkstyle:MissingSwitchDefault*/
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen_1);

<<<<<<< HEAD
        exitGame = (Button) findViewById(R.id.endScreenButton);
        playerName = (TextView) findViewById(R.id.playerNameDisplay);
        difficulty = (TextView) findViewById(R.id.difficultyDisplay);
        sprite = (ImageView) findViewById(R.id.sprite);
        health = (ImageView) findViewById(R.id.health);
        timerText = (TextView) findViewById(R.id.timerTextView);
        scoreText = (TextView) findViewById(R.id.scoreTextView);
=======
        //No need to cast exitGame = (Button) ..
        exitGame = findViewById(R.id.endScreenButton);
        playerName = findViewById(R.id.playerNameDisplay);
        difficulty = findViewById(R.id.difficultyDisplay);
        sprite = findViewById(R.id.sprite);
        health = findViewById(R.id.health);
        timerText = findViewById(R.id.timerTextView);
>>>>>>> 343af55c1d72e0a270b7568027f2b58b3d9e7315

        difficultyNum = getIntent().getIntExtra("difficulty", 1);
        playerNameStr = getIntent().getStringExtra("playerName");
        spriteNum = getIntent().getIntExtra("spriteNum", 1);

        playerName.setText(playerNameStr);

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams)
                health.getLayoutParams();

        timer = new Timer(System.currentTimeMillis(), timerText, scoreText);
        timer.runTimer();

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
            timer.stopTimer();
            Intent endScreen = new Intent(GameScreen1.this, GameEnd.class);
            startActivity(endScreen);
            finish();
        });

    }
}


