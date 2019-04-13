package com.doogies.savepups.states;

import com.doogies.savepups.Game;
import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;

import java.awt.*;

public class MenuState extends State{

    public MenuState(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        //temp code
        g.drawImage(Assets.damagedFloor, 0, 0, null);
    }

}
