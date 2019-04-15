package com.doogies.savepups.states;

import com.doogies.savepups.Game;
import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.entities.statics.Bed;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.tiles.Tile;
import com.doogies.savepups.world.Worlds;

import java.awt.*;

public class GameState extends State {

    private Worlds world;

    public GameState(Handler handler){
        super(handler);
        world = new Worlds(handler, "res/worlds/testWorld.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

}
