package com.example.dungeoncrawling;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameEnd extends AppCompatActivity {

    private Timer timer;
    private TextView timerText;
    private Button resetGame;

    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);

        timerText = (TextView) findViewById(R.id.timerTextView);
        timer = new Timer(System.currentTimeMillis(), timerText, scoreText);
        timer.runTimer();

        //Reset button
        resetGame = findViewById(R.id.ResetButton);

        resetGame.setOnClickListener(v -> {
            Intent createPlayer = new Intent(GameEnd.this, MainActivity.class);
            startActivity(createPlayer);
            finish();
        });
    }
}
