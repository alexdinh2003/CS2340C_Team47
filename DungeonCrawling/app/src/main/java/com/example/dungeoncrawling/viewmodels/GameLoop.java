package com.example.dungeoncrawling.viewmodels;

public class GameLoop implements Runnable {

    private Thread gameThread;
    private GameMap map;
    private boolean running;

    public GameLoop(GameMap map) {
        gameThread = new Thread(this);
        this.map = map;
        this.running = true;
    }

    //runs the game and updates game screen each loop
    @Override
    public void run() {
        long lastDelta = System.nanoTime();
        long nanoSec = 1_000_000_000;
        double delta = 0;

        while (this.running) {
            long nowDelta = System.nanoTime();
            double timeSinceLastDelta = nowDelta - lastDelta;
            delta += (timeSinceLastDelta / nanoSec);
            if (delta > 0.5) {
                map.render();
                map.update();
                delta = 0;
            }

            lastDelta = nowDelta;
        }
    }

    public void startGameLoop() {
        gameThread.start();
    }

    public void endGameLoop() {
        this.running = false;
    }
}
