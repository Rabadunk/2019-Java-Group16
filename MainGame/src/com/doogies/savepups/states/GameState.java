package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.world.World;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameState extends State {

    private World bedroomWorld;
    private World hallwayWorld;
    private World testWorld;
    private ArrayList<World> worlds;

    private boolean worldChanged = false;

    public GameState(Handler handler){
        super(handler);
        bedroomWorld = new World(handler, "res/worlds/bedroom.txt", 100);
        hallwayWorld = new World(handler, "res/worlds/hallway.txt", 101);
        testWorld = new World(handler, "res/worlds/testWorld.txt", 102);
        worlds = new ArrayList<>();

        worlds.add(bedroomWorld);
        worlds.add(hallwayWorld);
        worlds.add(testWorld);

        handler.setWorld(testWorld);
    }

    @Override
    public void tick() {

        Player player = handler.getWorld().getEntityManager().getPlayer();

        if(player.inEntry()) {
            for( World world : worlds ) {
                if(world.getID() == player.getCurrentTileID()) {

                    handler.setWorld(world);
                }
            }

            worldChanged = true;
        }

        handler.getWorld().tick();
    }

    @Override
    public void render(Graphics g) {

        if(worldChanged) {
            g.setColor(Color.BLACK);
            g.fillRect(0 ,0, handler.getWidth(), handler.getHeight());
            worldChanged = false;
        }
        handler.getWorld().render(g);
    }

}
