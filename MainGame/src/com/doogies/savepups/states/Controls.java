package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Controls extends State{


    public Controls(Handler handler){
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

        renderControls(g);

    }

    public void renderControls(Graphics g){
        Text.drawString(g,"Controls", 500, 100, true, Color.WHITE, Assets.fontTitle);

        Text.drawString(g,"W or ArrowKey Up = Walk up", 500, 280, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g,"S or ArrowKey Down = Walk Down", 500, 320, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g,"A or ArrowKey Left = Walk Left", 500, 360, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g,"D or ArrowKey Right = Walk Right", 500, 400, true, Color.WHITE, Assets.fontChenSmaller);

        Text.drawString(g,"J = attack", 500, 470, true, Color.WHITE, Assets.fontChenSmaller);
        Text.drawString(g,"G = Turn into bed", 500, 510, true, Color.WHITE, Assets.fontChenSmaller);

        Text.drawString(g,"Press ENTER to exit", 500, 680, true, Color.WHITE, Assets.font28);
    }

    @Override
    public void startMusic() {

    }

    @Override
    public void stopMusic() {

    }
}
