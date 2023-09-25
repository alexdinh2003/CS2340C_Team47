package com.example.dungeoncrawling;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameScreen1 extends AppCompatActivity {

        Button exitGame;
        TextView playerName;
        TextView difficulty;
        ImageView sprite;
        ImageView health;
        int difficultyNum;
        String playerNameStr;
        int spriteNum;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.game_screen_1);

            exitGame = (Button) findViewById(R.id.endScreenButton);
            playerName = (TextView) findViewById(R.id.playerNameDisplay);
            difficulty = (TextView) findViewById(R.id.difficultyDisplay);
            sprite = (ImageView) findViewById(R.id.sprite);
            health = (ImageView) findViewById(R.id.health);

            difficultyNum = getIntent().getIntExtra("difficulty", 1);
            playerNameStr = getIntent().getStringExtra("playerName");
            spriteNum = getIntent().getIntExtra("spriteNum", 1);

            playerName.setText(playerNameStr);

            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) health.getLayoutParams();
            switch(difficultyNum) {
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
            }

            exitGame.setOnClickListener(v -> {
                Intent endScreen = new Intent(GameScreen1.this, GameEnd.class);
                startActivity(endScreen);
                finish();
            });
        }
}



