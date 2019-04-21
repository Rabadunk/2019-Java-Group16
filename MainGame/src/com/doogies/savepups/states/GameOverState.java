package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioPlayer;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState extends State {

    public AudioPlayer gameMusic;

    public GameOverState(Handler handler){
        super(handler);
        gameMusic = new AudioPlayer();
        gameMusic.setFileMusic("NoHope");
    }


    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
            State.setState(handler.getGame().menuState);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, handler.getWidth(), handler.getHeight());
        Text.drawString(g,"GAME OVER", 500, 200, true, Color.WHITE, Assets.fontTitle);
        Text.drawString(g,"Press p to exit", 500, 600, true, Color.WHITE, Assets.font28);

    }

    @Override
    public void startMusic() { gameMusic.play(); }

    @Override
    public void stopMusic() {
        gameMusic.stop();
    }
}
