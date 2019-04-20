package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioPlayer;
import com.doogies.savepups.audio.Music;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;
import com.doogies.savepups.house.HouseGraph;
import com.doogies.savepups.house.Room;
import com.doogies.savepups.ui.UIImageButton;
import com.doogies.savepups.ui.UIManager;
import com.doogies.savepups.ui.UIObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
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
        menuMusic.setFile("menu");
        //menuMusic.play();

        houseGraph = new HouseGraph(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);


        uiManager.addObject( new UIImageButton(100, 150, 300, 150, Assets.playButton, () -> {
            if (handler.getKeyManager().enter) {
                State.setState(handler.getGame().gameState);
            }
        }, () -> {
            handler.getMouseManager().setUiManager(null);
            State.setState(handler.getGame().gameState);

        }));

        uiManager.addObject( new UIImageButton(100, 350, 300, 150, Assets.scoreButton, () -> {
            if(handler.getKeyManager().enter) {
                State.setState(handler.getGame().gameState);
                handler.setRoom(houseGraph.getRoom(2));
            }
        }, () -> {
            handler.getMouseManager().setUiManager(null);
            State.setState(handler.getGame().gameState);
            handler.setRoom(houseGraph.getRoom(2));

        }));

        uiManager.addObject( new UIImageButton(100, 550, 300, 150, Assets.quitButton, () -> {
            if(handler.getKeyManager().enter) {
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
        Text.drawString(g, "Save the Puppies!", 550, 75, true, Color.WHITE, Assets.fontTitle);
    }

    @Override
    public void startMusic() {
        menuMusic.play();
    }

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
