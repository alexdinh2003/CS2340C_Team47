package com.example.dungeoncrawling;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameEnd extends AppCompatActivity {

    private Timer timer;
    private TextView timerText;
    private TextView scoreText;
    private Button resetGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);

        timerText = findViewById(R.id.timerTextView);
        scoreText = findViewById(R.id.scoreTextView);
        timer = new Timer(System.currentTimeMillis(), timerText, scoreText);
        timer.displayOldTime(timerText);
        timer.displayScore(scoreText);

        //Reset button
        resetGame = findViewById(R.id.ResetButton);

        resetGame.setOnClickListener(v -> {
            timer.stopTimer();
            timer.resetTimer();
            Intent createPlayer = new Intent(GameEnd.this, MainActivity.class);
            startActivity(createPlayer);
            finish();
        });
    }
}
