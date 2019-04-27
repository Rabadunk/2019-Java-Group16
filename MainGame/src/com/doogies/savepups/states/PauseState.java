package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;
import com.doogies.savepups.ui.UIImageButton;
import com.doogies.savepups.ui.UIManager;
import com.doogies.savepups.ui.UIObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class PauseState extends State {

    private UIManager uiManager;
    private int indexOfActiveButton = 0;
    private int lastIndex = 0;
    private boolean hasBeenPressed = false;

    public PauseState(Handler handler) {
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        // Resume button
        uiManager.addObject( new UIImageButton(handler.getWidth()/2 - 150, 310, 300, 100, Assets.blankButton, () -> {
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
                stopMusic();
                State.setState(handler.getGame().gameState);
            }
        }, () -> {
            handler.getMouseManager().setUiManager(null);
            stopMusic();
            State.setState(handler.getGame().gameState);
        }));

        // Main menu button
        uiManager.addObject( new UIImageButton(handler.getWidth()/2 - 150, 420, 300, 100, Assets.blankButton, () -> {
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
                stopMusic();
                State.setState(handler.getGame().menuState);
            }
        }, () -> {
            handler.getMouseManager().setUiManager(null);
            stopMusic();
            State.setState(handler.getGame().menuState);
        }));



    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
            State.setState(handler.getGame().gameState);
        }
        uiManager.tick();
        getInput();
        uiManager.getObjects().get(indexOfActiveButton).setSelected(true);
        checkInputs();
    }

    @Override
    public void render(Graphics g) {

        handler.getRoom().render(g);
        g.drawImage(Assets.tint, 0,0, null);

       // g.setColor(Color.blue);
     //   g.fillRect(0, 0, handler.getWidth(), handler.getHeight());

        //temp code
        g.setColor(Color.RED);
        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
        uiManager.render(g);

        // Title
        Text.drawString(g, "Paused", handler.getWidth()/2, 100, true, Color.WHITE, Assets.fontTitle);

        // Button texts
        Text.drawString(g, "Resume", handler.getWidth()/2, 310 + 50,true, Color.WHITE, Assets.font28);
        Text.drawString(g, "Exit to main menu", handler.getWidth()/2, 420 + 50,true, Color.WHITE, Assets.font28);

    }

    public void getInput() {

        if (!hasBeenPressed) {
            if (handler.getKeyManager().up) {
                indexOfActiveButton--;
            } else if (handler.getKeyManager().down) {
                indexOfActiveButton++;
            }

            if (indexOfActiveButton > uiManager.getObjects().size() - 1 || indexOfActiveButton < 0) {
                indexOfActiveButton = lastIndex;
            }

            if(lastIndex != indexOfActiveButton) {
                hasBeenPressed = true;
            }

            lastIndex = indexOfActiveButton;
        }

        else {
            try {
                TimeUnit.NANOSECONDS.sleep(200000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            hasBeenPressed = false;
        }
    }

    public void checkInputs(){
        if(handler.getKeyManager().up || handler.getKeyManager().down){
            for(UIObject o : uiManager.getObjects()) {
                o.setHovering(false);
            }
//        } else if (handler.getMouseManager().isMoved()) {
//            for(UIObject o : uiManager.getObjects()) {
//                o.setSelected(false);
//            }

        }
    }

    @Override
    public void startMusic() {

    }

    @Override
    public void stopMusic() {

    }
}
