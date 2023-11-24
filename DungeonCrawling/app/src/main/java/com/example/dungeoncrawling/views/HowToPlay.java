package com.example.dungeoncrawling.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dungeoncrawling.R;

public class HowToPlay extends AppCompatActivity {
    private Button back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.how_to_play);


        back = findViewById(R.id.backButton);

        back.setOnClickListener(v -> {
            Intent createPlayer = new Intent(HowToPlay.this, MainActivity.class);
            startActivity(createPlayer);
            finish();
        });



    }
}
