package com.example.dungeoncrawling.viewmodels;

public class GameLoop implements Runnable {

    private Thread gameThread;
    private GameMap map;

    public GameLoop(GameMap map) {
        gameThread = new Thread(this);
        this.map = map;
    }

    @Override
    public void run() {
        long lastDelta = System.nanoTime();
        long nanoSec = 1_000_000_000;
        double delta = 0;

        while (true) {

            long nowDelta = System.nanoTime();
            double timeSinceLastDelta = nowDelta - lastDelta;
            delta += (timeSinceLastDelta / nanoSec);
            if (delta > 0.5) {
                map.update();
                map.render();
                delta = 0;
            }

            lastDelta = nowDelta;
        }
    }

    public void startGameLoop() {
        gameThread.start();
    }
}
