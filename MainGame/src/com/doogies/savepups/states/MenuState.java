package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.ui.ClickListener;
import com.doogies.savepups.ui.UIImageButton;
import com.doogies.savepups.ui.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MenuState extends State{

    private UIManager uiManager;
    private int indexOfActiveButton = 0;
    private int lastIndex = 0;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject( new UIImageButton(100, 100, 300, 150, Assets.playButton, () -> {
            if (handler.getKeyManager().enter) {
                State.setState(handler.getGame().gameState);
            }
        }, () -> {
            handler.getMouseManager().setUiManager(null);
            State.setState(handler.getGame().gameState);

        }));

        uiManager.addObject(new UIImageButton(100, 300, 300, 150, Assets.quitButton, () -> {
            if (handler.getKeyManager().enter) {
                JFrame frame = handler.getGame().getDisplay().getFrame();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
            }, () -> {
                JFrame frame = handler.getGame().getDisplay().getFrame();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

            }));
    }

    @Override
    public void tick() {
        System.out.println(handler.getMouseManager().getMouseX() + "  " + handler.getMouseManager().getMouseY());
        uiManager.tick();
        getIndexOfActiveButton();
        uiManager.getObjects().get(indexOfActiveButton).setSelected(true);
    }

    @Override
    public void render(Graphics g) {
        //temp code
        g.setColor(Color.RED);
        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
        uiManager.render(g);
    }

    public void getIndexOfActiveButton() {

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
