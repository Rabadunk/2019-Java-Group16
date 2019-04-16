package com.doogies.savepups;

import com.doogies.savepups.display.Display;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.GameCamera;
import com.doogies.savepups.input.KeyManager;
import com.doogies.savepups.input.MouseManager;
import com.doogies.savepups.states.GameState;
import com.doogies.savepups.states.MenuState;
import com.doogies.savepups.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;

public class Game implements Runnable {

    public String title;

    private boolean running = false;
    private int width, height;
    private int fps = 60;
    private double timePerTick = 1000000000 / fps; // 1 billion ns in 1s

    // Views
    private BufferStrategy bs;
    private Graphics g;
    private Display display;
    private Thread thread;

    //States
    public State gameState;
    public State menuState;

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

        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // returns current time is ns
        long timer = 0;
        int ticks = 0;

        while(running) {
            now = System.nanoTime();
            delta += deltaCalculation(now, lastTime);
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    private double deltaCalculation(long now, long lastTime) {
        return (now - lastTime) / timePerTick;
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
