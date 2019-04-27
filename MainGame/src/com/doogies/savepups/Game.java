package com.doogies.savepups;

import com.doogies.savepups.display.Display;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.GameCamera;
import com.doogies.savepups.input.KeyManager;
import com.doogies.savepups.input.MouseManager;
import com.doogies.savepups.states.*;
import com.doogies.savepups.utils.GameTimer;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    public String title;

    private boolean running = false;
    private int width, height;
    private int fps = 60;
    private double timePerTick = 1000000000 / fps; // 1 billion ns in 1s
    private long timer = 0;
    private int ticks = 0;
    private double delta = 0;

    private GameTimer timeTaken;

    // Views
    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    private Thread thread;

    //States
    public State gameState;
    public State menuState;
    public State gameEndState;
    public State scoreboard;
    public State controls;
    public State pause;

    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    // Camera follows player
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.keyManager = new KeyManager();
        mouseManager = new MouseManager();

    }


    public synchronized void start() {
        if(running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private void init() {
        DisplayInit();
        GameInit();
        StateInit();
        timeTaken = new GameTimer(handler);
    }

    private void DisplayInit() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
    }

    private void GameInit() {
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
    }

    private void StateInit() {
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        gameEndState = new GameEndState(handler);
        scoreboard = new Scoreboard(handler);
        controls = new Controls(handler);
        pause = new PauseState(handler);
        State.setState(menuState);
    }

    private void tick() {
        keyManager.tick();
        if(State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render() {
        if(isBuffering()) {
            return;
        }
        DrawScreen();
    }

    private boolean isBuffering() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return true;
        }
        return false;
    }

    private void DrawScreen() {
        g = bs.getDrawGraphics();

        // Clear screen
        g.clearRect(0, 0, width, height);

        if(State.getState() != null) {
            State.getState().render(g);
        }

        //show and dispose drawings
        bs.show();
        g.dispose();
    }

    public void run() {
        init();
        long now;
        long lastTime = System.nanoTime(); // returns current time is ns

        while(running) {
            now = System.nanoTime();
            updateDeltaAndTimer(now, lastTime);
            lastTime = now;
            stabiliseTicks();
            resetTimer();
        }

        stop();
    }

    private void updateDeltaAndTimer(long now, long lastTime) {
        delta += deltaCalculation(now, lastTime);
        timer += now - lastTime;
    }

    private double deltaCalculation(long now, long lastTime) {
        return (now - lastTime) / timePerTick;
    }

    private void stabiliseTicks() {
        if(delta >= 1){
            tick();
            render();
            ticks++;
            delta--;
        }
    }

    private void resetTimer() {
        if(timer >= 1000000000) {
            ticks = 0;
            timer = 0;
        }
    }

    public synchronized void stop() {
        if(!running) return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Getters and setters

    public KeyManager getKeyManager() { return keyManager; }

    public MouseManager getMouseManager(){ return mouseManager; }

    public GameCamera getGameCamera() { return gameCamera; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public Display getDisplay() { return display; };

}
