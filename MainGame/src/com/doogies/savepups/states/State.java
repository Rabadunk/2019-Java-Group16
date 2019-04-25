package com.doogies.savepups.states;

import com.doogies.savepups.Game;
import com.doogies.savepups.Handler;

import java.awt.*;

public abstract class State {

    private static State currentState = null;
    protected static Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public static void setState(State state) {
        if(state == handler.getGame().gameState) {
            handler.getGame().menuState.stopMusic();
            handler.getGame().gameEndState.stopMusic();
        }
        if (state == handler.getGame().menuState){
            handler.getGame().menuState.startMusic();
            handler.getGame().gameEndState.stopMusic();
        }

        if(state == handler.getGame().gameEndState){
            handler.getGame().menuState.stopMusic();
            handler.getGame().gameEndState.startMusic();
        }
        currentState = state;

    }

    public static State getState() {
        return currentState;
    }


    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void startMusic();
    public abstract void stopMusic();

}
