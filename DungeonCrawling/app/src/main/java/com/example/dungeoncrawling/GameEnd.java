package com.example.dungeoncrawling;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameEnd extends AppCompatActivity {

    private Timer timer;
    private TextView timerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);

        timerText = (TextView) findViewById(R.id.timerTextView);
        timer = new Timer(System.currentTimeMillis(), timerText);
        timer.runTimer();

    }
}
