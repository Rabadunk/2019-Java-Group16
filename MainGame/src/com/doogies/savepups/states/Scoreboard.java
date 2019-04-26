package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Scoreboard extends State{


    public Scoreboard(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            State.setState(handler.getGame().menuState);
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, handler.getWidth(), handler.getHeight());

        Text.drawString(g,"High Scores", 500, 100, true, Color.WHITE, Assets.fontTitle);

        // Displaying scores

        for(int i = 0; i < 10; i++){
            Text.drawString(g, handler.getPlayer().getHighScoreManager().getArrayListScores().get(i).getName(),
                    230, 240 + i * 40, false, Color.WHITE, Assets.font28);
        }
        for(int i = 0; i < 10; i++){
            Text.drawString(g, Integer.toString(handler.getPlayer().getHighScoreManager().getArrayListScores().get(i).getScore()),
                    600, 240 + i * 40, false, Color.WHITE, Assets.font28);
        }

        Text.drawString(g,"Press ENTER to exit", 500, 680, true, Color.WHITE, Assets.font28);

    }

    @Override
    public void startMusic() {

    }

    @Override
    public void stopMusic() {

    }
}
