package com.example.dungeoncrawling.model.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.dungeoncrawling.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 100;
    private static final int SPRITE_HEIGHT_PIXELS = 100;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.dungeontileset,
                bitmapOptions);
    }
    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getVWallSprite(boolean left) {
        if (left) {
            return new Sprite(this, new Rect(80, 590,
                    80 + SPRITE_WIDTH_PIXELS, 590 + SPRITE_HEIGHT_PIXELS));
        } else {
            return new Sprite(this, new Rect(210 - SPRITE_WIDTH_PIXELS + 43, 590,
                    210 + 43, 590 + SPRITE_HEIGHT_PIXELS));
        }
    }

    public Sprite getCornerWallSprite(boolean left) {
        if (left) {
            return new Sprite(this, new Rect(80, 510,
                    80 + SPRITE_WIDTH_PIXELS, 510 + SPRITE_HEIGHT_PIXELS));
        } else {
            return new Sprite(this, new Rect(210 - SPRITE_WIDTH_PIXELS + 43, 510,
                    253, 510 + SPRITE_HEIGHT_PIXELS));
        }
    }

    public Sprite getHWallSprite(boolean banner) {
        if (banner) {
            return new Sprite(this, new Rect(16, 170,
                    16 + SPRITE_WIDTH_PIXELS, 170 + SPRITE_HEIGHT_PIXELS * 3));
        }
        return new Sprite(this, new Rect(16, 28,
                16 + SPRITE_WIDTH_PIXELS, 28 + SPRITE_HEIGHT_PIXELS));
    }

    public Sprite getExitSprite() {
        return new Sprite(this, new Rect(275,
                750, 275 + SPRITE_WIDTH_PIXELS, 750 + SPRITE_HEIGHT_PIXELS));
    }

    public Sprite getFloorSprite() {
        return new Sprite(this, new Rect(16, 240,
                16 + SPRITE_WIDTH_PIXELS, 240 + SPRITE_HEIGHT_PIXELS));
    }

    public Sprite getPitSprite() {
        return new Sprite(this, new Rect(335, 555,
                335 + SPRITE_WIDTH_PIXELS, 555 + SPRITE_HEIGHT_PIXELS));
    }

    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol * SPRITE_WIDTH_PIXELS,
                idxRow * SPRITE_HEIGHT_PIXELS,
                (idxCol + 1) * SPRITE_WIDTH_PIXELS,
                (idxRow + 1) * SPRITE_HEIGHT_PIXELS
        ));
    }

    public Sprite getPlayer(int id) {
        if (id == 0) {
            return new Sprite(this, new Rect(528, 50,
                    595, 105));
        } else if (id == 1) {
            return new Sprite(this, new Rect(595, 560,
                    650, 618));
        } else {
            return new Sprite(this, new Rect(595, 820,
                    658, 875));
        }

    }
}

