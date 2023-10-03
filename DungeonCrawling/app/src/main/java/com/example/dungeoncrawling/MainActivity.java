package com.example.dungeoncrawling;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        start = findViewById(R.id.startButton);
        exit = findViewById(R.id.exitButton);

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
