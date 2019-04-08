package com.doogies.savepups.states;

import com.doogies.savepups.graphics.Assets;

import java.awt.*;

public class GameState extends State {

    public GameState(){

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.computer, 0, 0, null);
    }

}
