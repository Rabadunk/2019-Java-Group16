package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.input.KeyManager;
import com.doogies.savepups.ui.UIImageButton;
import com.doogies.savepups.ui.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class MenuState extends State{

    private UIManager uiManager;
    private int indexOfActiveButton = 0;
    private int lastIndex = 0;

    public MenuState(Handler handler){
        super(handler);

        uiManager = new UIManager(handler);

        uiManager.addObject( new UIImageButton(100, 100, 300, 150, Assets.playButton, () -> {
            if(handler.getKeyManager().enter) {
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject( new UIImageButton(100, 300, 300, 150, Assets.scoreButton, () -> {
            if(handler.getKeyManager().enter) {
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject( new UIImageButton(100, 500, 300, 150, Assets.quitButton, () -> {
            if(handler.getKeyManager().enter) {
                JFrame frame = handler.getGame().getDisplay().getFrame();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
        getInput();
        uiManager.getObjects().get(indexOfActiveButton).setSelected(true);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        //temp code
        uiManager.render(g);
    }

    public void getInput() {

        if(handler.getKeyManager().up) {
            indexOfActiveButton--;
        }

        else if (handler.getKeyManager().down){
            indexOfActiveButton++;
        }

        if(indexOfActiveButton > uiManager.getObjects().size() - 1 || indexOfActiveButton < 0) {
            indexOfActiveButton = lastIndex;
        }

        lastIndex = indexOfActiveButton;

    }

}
