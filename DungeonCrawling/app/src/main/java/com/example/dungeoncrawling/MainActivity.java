package com.example.dungeoncrawling;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.startButton);
        exit = (Button) findViewById(R.id.exitButton);

        start.setOnClickListener(v -> {
            Intent createPlayer = new Intent(MainActivity.this, PlayerCreation.class);
            startActivity(createPlayer);
            finish();
        });

        exit.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });

    }
}
