package com.example.dungeoncrawling;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerCreation extends AppCompatActivity {

    private EditText playerName;
    private Button createPlayer;
    private ImageButton sprite1;
    private ImageButton sprite2;
    private ImageButton sprite3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player);

        playerName = (EditText) findViewById(R.id.playerName);
        createPlayer = (Button) findViewById(R.id.createPlayerButton);
        sprite1 = (ImageButton) findViewById(R.id.playerSprite1);
        sprite2 = (ImageButton) findViewById(R.id.playerSprite2);
        sprite3 = (ImageButton) findViewById(R.id.playerSprite3);

        createPlayer.setOnClickListener(v -> {
            Intent createPlayer = new Intent(PlayerCreation.this, PlayerCreation.class);
            startActivity(createPlayer);
            finish();
        });

    }
}
