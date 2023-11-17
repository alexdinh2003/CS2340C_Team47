package com.example.dungeoncrawling.model.graphics;
import android.content.Context;
import android.graphics.Canvas;

public class HP {
    private static final int HEART_WIDTH = 64;
    private static int start;
    private static HP hp;
    private Sprite fullHeart;
    private Sprite halfHeart;
    private Sprite emptyHeart;
    private SpriteSheet spriteSheet;
    private int maxHearts;
    private Context context;


    private HP(SpriteSheet spriteSheet, int difficulty) {
        setSpriteSheet(spriteSheet);
        setDifficulty(difficulty);
    }

    private HP(int difficulty) {
        setDifficulty(difficulty);
    }

    private HP(Context context) {
        this.context = context;
    }

    public static HP getInstance(SpriteSheet spriteSheet, int difficulty) {
        if (hp == null) {
            hp = new HP(spriteSheet, difficulty);
        }
        return hp;
    }

    public static HP getInstance(int difficulty) {
        if (hp == null) {
            hp = new HP(difficulty);
        }
        return hp;
    }

    public static HP getInstance() {
        return getInstance(0);
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
        fullHeart = spriteSheet.getFullHeart();
        halfHeart = spriteSheet.getHalfHeart();
        emptyHeart = spriteSheet.getEmptyHeart();
    }

    public void setDifficulty(int difficulty) {
        switch (difficulty) {
        case 1: // Easy
            maxHearts = 5;
            start = 230;
            break;
        case 2: // Medium
            maxHearts = 4;
            start = 250;
            break;
        case 3: // Hard
            maxHearts = 3;
            start = 350;
            break;
        default:
            maxHearts = 0;
            start = 0;
        }
    }

    public int getDifficulty() {
        switch (maxHearts) {
        case 5: // Easy
            return 1;
        case 4: // Medium
            return 2;
        case 3: // Hard
            return 3;
        default:
            return 0;
        }
    }

    public int getMaxHearts() {
        return maxHearts;
    }

    public void draw(Canvas canvas, int currentHealth) {
        if (this.spriteSheet == null) {
            System.out.println("Sorry, it looks like you never specified a "
                    + "sprite sheet for the health.");
            return;
        }

        int fullHearts = (int) (currentHealth / 2);

        // Draw full hearts
        for (int i = 0; i < fullHearts; i++) {
            fullHeart.draw(
                    canvas,
                    start + i * HEART_WIDTH,
                    40);
            //canvas.drawBitmap(fullHeart, i * fullHeart.getWidth(), 0, null);
        }

        // Draw half hearts (if any)
        if (currentHealth % 2 == 1) {
            halfHeart.draw(
                    canvas,
                    start + fullHearts * HEART_WIDTH,
                    40);
            //canvas.drawBitmap(halfHeart, remainingHearts * fullHeart.getWidth(), 0, null);
            fullHearts++;
        }

        // Draw empty hearts for remaining slots
        for (int i = fullHearts; i < maxHearts; i++) {
            emptyHeart.draw(
                    canvas,
                    start + i * HEART_WIDTH,
                    40);
            //canvas.drawBitmap(emptyHeart, i * fullHeart.getWidth(), 0, null);
        }
    }
}
