package com.doogies.savepups.states;

import com.doogies.savepups.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PathfindingDemoState extends State{

    public PathfindingDemoState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            State.setState(handler.getGame().menuState);
        }

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void startMusic() {

    }

    @Override
    public void stopMusic() {

    }
}
