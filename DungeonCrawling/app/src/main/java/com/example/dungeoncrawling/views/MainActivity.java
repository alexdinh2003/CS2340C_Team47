package com.example.dungeoncrawling.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeoncrawling.viewmodels.PlayerCreation;
import com.example.dungeoncrawling.R;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button exit;
    private Button howToPlay;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        start = findViewById(R.id.startButton);
        exit = findViewById(R.id.exitButton);
        howToPlay = findViewById(R.id.howToPlayButton);

        start.setOnClickListener(v -> {
            Intent createPlayer = new Intent(MainActivity.this, PlayerCreation.class);
            startActivity(createPlayer);
            finish();
        });

        exit.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });

        howToPlay.setOnClickListener(V -> {
            Intent createPlayer1 = new Intent(MainActivity.this, HowToPlay.class);
            startActivity(createPlayer1);
            finish();
        });

    }
}
