package com.example.dungeoncrawling;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.dungeoncrawling.graphics.SpriteSheet;
import com.example.dungeoncrawling.map.Tilemap;

class Map1View extends SurfaceView implements SurfaceHolder.Callback {

    private final Tilemap tilemap;

    public Map1View(Context context) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        SpriteSheet spriteSheet = new SpriteSheet(context);

        tilemap = new Tilemap(spriteSheet);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        tilemap.draw(canvas);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Log.d("Map1View.java", "surfaceCreated()");
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        Canvas canvas = surfaceHolder.lockCanvas();
        draw(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int i, int i1, int i2) {
        Log.d("Map1View.java", "surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        Log.d("Map1View.java", "surfaceDestroyed()");
    }
}
