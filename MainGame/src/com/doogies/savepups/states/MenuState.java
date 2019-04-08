package com.doogies.savepups.states;

import com.doogies.savepups.graphics.Assets;

import java.awt.*;

public class MenuState extends State{

    public MenuState(){

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.computer, 0, 0, null);
    }

}
