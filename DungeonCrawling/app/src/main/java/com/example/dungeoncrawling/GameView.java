package com.example.dungeoncrawling;

import android.content.Context;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    //intro view
    private Thread thread;
    private boolean isPlaying;

    public GameView(Context context) {
        super(context);
    }

    @Override
    public void run() {

        while (isPlaying) {

            update();
            draw();
            sleep();
        }
    }

    private void update () {

    }

    private void draw () {

    }

    private void sleep () {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void resume () {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause () {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
