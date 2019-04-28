package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StoryState extends State {

    public StoryState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            State.setState(handler.getGame().gameState);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, handler.getWidth(), handler.getHeight());

        Text.drawString(g, "Your step mother is evil! She has taken away everyone's puppies",
                100, 500, false, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g, "Go and save them",
                100, 540, false, Color.WHITE, Assets.fontChenSmaller);


        Text.drawString(g,"Press ENTER to continue to game", 500, 680, true, Color.WHITE, Assets.font28);

    }

    @Override
    public void startMusic() {

    }

    @Override
    public void stopMusic() {

    }
}
