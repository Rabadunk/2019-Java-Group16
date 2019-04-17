package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.world.World;

import java.awt.*;

public class GameState extends State {

    private World bedroomWorld;
    private World hallwayWorld;
    private World testWorld;

    public GameState(Handler handler){
        super(handler);
        bedroomWorld = new World(handler, "res/worlds/bedroom.txt", 0);
        hallwayWorld = new World(handler, "res/worlds/hallway.txt", 1);
        testWorld = new World(handler, "res/worlds/testWorld.txt", 2);
        handler.setWorld(testWorld);
    }

    @Override
    public void tick() {
        handler.getWorld().tick();
    }

    @Override
    public void render(Graphics g) {
        handler.getWorld().render(g);
    }

}
