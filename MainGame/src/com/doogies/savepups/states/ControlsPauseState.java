package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ControlsPauseState extends State {

    public ControlsPauseState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            State.setState(handler.getGame().pause);
        }
    }

    @Override
    public void render(Graphics g) {
        handler.getRoom().render(g);
        g.drawImage(Assets.tint, 0,0, null);

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

        Text.drawString(g,"Press ESC to exit", 500, 680, true, Color.WHITE, Assets.font28);
    }

    @Override
    public void startMusic() {

    }

    @Override
    public void stopMusic() {

    }
}
