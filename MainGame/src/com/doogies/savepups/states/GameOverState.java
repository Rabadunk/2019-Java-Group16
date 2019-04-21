package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState extends State {

    public GameOverState(Handler handler){
        super(handler);
    }


    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
            State.setState(handler.getGame().menuState);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0,0, handler.getWidth(), handler.getHeight());
        Text.drawString(g,"GAME OVER", 500, 500, true, Color.WHITE, Assets.fontTitle);

    }

    @Override
    public void startMusic() {

    }

    @Override
    public void stopMusic() {

    }
}
