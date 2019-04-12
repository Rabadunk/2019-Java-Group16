package com.doogies.savepups.states;

import com.doogies.savepups.Game;
import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.tiles.Tile;
import com.doogies.savepups.world.Worlds;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private Worlds world;

    public GameState(Handler handler){
        super(handler);
        world = new Worlds(handler, "res/worlds/bedroom.txt");
        handler.setWorld(world);
        player = new Player(handler,100, 100);
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }

}
