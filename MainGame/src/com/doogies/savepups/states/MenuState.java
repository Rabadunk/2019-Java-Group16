package com.doogies.savepups.states;

import com.doogies.savepups.Game;
import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.ui.UIImageButton;
import com.doogies.savepups.ui.UIManager;

import javax.swing.*;
import java.awt.*;

public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);

        uiManager.addObject( new UIImageButton(100, 100, 300, 150, Assets.playButton));
    }

    @Override
    public void tick() {
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        //temp code
        uiManager.render(g);
    }

}
