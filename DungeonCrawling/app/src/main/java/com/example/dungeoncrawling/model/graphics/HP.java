package com.example.dungeoncrawling.model.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.dungeoncrawling.R;

public class HP {
    private Bitmap fullHeart;
    private Bitmap halfHeart;
    private Bitmap emptyHeart;
    private int maxHearts;

    public HP(Context context, int difficulty) {
        // Load heart images based on difficulty
        switch (difficulty) {
            case 1: // Easy
                fullHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.five_hearts);
                //halfHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.half_heart_easy);
                //emptyHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_heart_easy);
                maxHearts = 5;
                break;
            case 2: // Medium
                fullHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.four_hearts);
                //halfHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.half_heart_medium);
                //emptyHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_heart_medium);
                maxHearts = 4;
                break;
            case 3: // Hard
                fullHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.three_hearts);
                //halfHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.half_heart_hard);
                //emptyHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_heart_hard);
                maxHearts = 3;
                break;
            default:
                // Default to easy
                fullHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.one_heart);
                //halfHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.half_heart_easy);
                //emptyHeart = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_heart_easy);
                maxHearts = 5;
        }
    }

    public void draw(Canvas canvas, int currentHealth) {
        int remainingHearts = Math.max(0, currentHealth / 2);

        // Draw full hearts
        for (int i = 0; i < remainingHearts; i++) {
            canvas.drawBitmap(fullHeart, i * fullHeart.getWidth(), 0, null);
        }

        // Draw half hearts (if any)
        if (currentHealth % 2 == 1) {
            canvas.drawBitmap(halfHeart, remainingHearts * fullHeart.getWidth(), 0, null);
            remainingHearts++;
        }

        // Draw empty hearts for remaining slots
        for (int i = remainingHearts; i < maxHearts; i++) {
            canvas.drawBitmap(emptyHeart, i * fullHeart.getWidth(), 0, null);
        }
    }
}
