package com.doogies.savepups.states;

import com.doogies.savepups.Game;
import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.input.KeyListener;
import com.doogies.savepups.ui.UIImageButton;
import com.doogies.savepups.ui.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MenuState extends State{

    private UIManager uiManager;
    private int indexOfActiveButton = 0;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);

        uiManager.addObject( new UIImageButton(100, 100, 300, 150, Assets.playButton, new KeyListener() {
            @Override
            public void onEnter() {
                if(handler.getKeyManager().enter) {
                    State.setState(handler.getGame().gameState);
                }
            }
        }));
        uiManager.addObject( new UIImageButton(100, 300, 300, 150, Assets.quitButton, new KeyListener() {
            @Override
            public void onEnter() {
                if(handler.getKeyManager().enter) {
                    handler.getGame().getDisplay().getFrame().setVisible(false);
                    handler.getGame().getDisplay().getFrame().dispose();
                }
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
        getIndexOfActiveButton();
        uiManager.getObjects().get(indexOfActiveButton).setSelected(true);
    }

    @Override
    public void render(Graphics g) {
        //temp code
        uiManager.render(g);
    }

    public void getIndexOfActiveButton() {

        if(handler.getKeyManager().up) {
            indexOfActiveButton = 0;
        }

        else if (handler.getKeyManager().down){
            indexOfActiveButton = 1;
        }

    }

}
