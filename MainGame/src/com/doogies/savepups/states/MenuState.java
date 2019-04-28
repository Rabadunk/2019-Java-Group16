package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioPlayer;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;
import com.doogies.savepups.house.HouseGraph;
import com.doogies.savepups.ui.UIImageButton;
import com.doogies.savepups.ui.UIManager;
import com.doogies.savepups.ui.UIObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

public class MenuState extends State{

    private UIManager uiManager;
    private int indexOfActiveButton = 0;
    private int lastIndex = 0;
    private boolean hasBeenPressed = false;

    public AudioPlayer menuMusic;

    private long lastTime, timer;
    private int index;

    private HouseGraph houseGraph;

    public MenuState(Handler handler){
        super(handler);

//        Music player = new Music("menu");
//        player.run();

        menuMusic = new AudioPlayer();
        menuMusic.setFileMusic("Happywalk");
        //menuMusic.play();

        houseGraph = new HouseGraph(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        // Play button
        uiManager.addObject( new UIImageButton(100, 200, 300, 100, Assets.blankButton, () -> {
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
                handler.newPlayer();
                handler.getGame().gameState = new GameState(handler);
                State.setState(handler.getGame().story);
            }
        }, () -> {
            handler.getMouseManager().setUiManager(null);
            handler.newPlayer();
            handler.getGame().gameState = new GameState(handler);
            State.setState(handler.getGame().story);

        }));

        // Demo button
        uiManager.addObject( new UIImageButton(100, 310, 300, 100, Assets.blankButton, () -> {
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
                stopMusic();
                State.setState(handler.getGame().demo);
            }
        }, () -> {
            handler.getMouseManager().setUiManager(null);
            stopMusic();
            State.setState(handler.getGame().demo);
        }));

        // Scoreboard button
        uiManager.addObject( new UIImageButton(100, 420, 300, 100, Assets.blankButton, () -> {
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
                stopMusic();
                State.setState(handler.getGame().scoreboard);
            }
        }, () -> {
            handler.getMouseManager().setUiManager(null);
            stopMusic();
            State.setState(handler.getGame().scoreboard);
        }));

        // Controls button
        uiManager.addObject( new UIImageButton(100, 530, 300, 100, Assets.blankButton, () -> {
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
                stopMusic();
                State.setState(handler.getGame().controls);
            }
        }, () -> {
            handler.getMouseManager().setUiManager(null);
            stopMusic();
            State.setState(handler.getGame().controls);
        }));

        // Quit button
        uiManager.addObject( new UIImageButton(100, 640, 300, 100, Assets.blankButton, () -> {
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
                closeGame();
            }
            }, () -> {
                closeGame();
            }));
    }


    public void closeGame(){
        JFrame frame = handler.getGame().getDisplay().getFrame();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void tick() {
        uiManager.tick();
        getInput();
        uiManager.getObjects().get(indexOfActiveButton).setSelected(true);
        checkInputs();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
        //temp code
        g.setColor(Color.RED);
        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
        uiManager.render(g);

        // Title
        Text.drawString(g, "Save the Puppies!", 550, 100, true, Color.WHITE, Assets.fontTitleBig);

        // Button texts
        Text.drawString(g, "Play", 100 + 150, 200 + 50,true, Color.WHITE, Assets.fontChen);
        Text.drawString(g, "Demo", 100 + 150, 310 + 50,true, Color.WHITE, Assets.fontChen);
        Text.drawString(g, "Scoreboard", 100 + 150, 420 + 50,true, Color.WHITE, Assets.fontChen);
        Text.drawString(g, "Controls", 100 + 150, 530 + 50,true, Color.WHITE, Assets.fontChen);
        Text.drawString(g, "Quit", 100 + 150, 640 + 50,true, Color.WHITE, Assets.fontChen);

        // Draw doogie
        g.drawImage(Assets.doogie,500, 250, 400, 400, null);
    }

    @Override
    public void startMusic() { menuMusic.play(); }

    @Override
    public void stopMusic() {
        menuMusic.stop();
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

}
