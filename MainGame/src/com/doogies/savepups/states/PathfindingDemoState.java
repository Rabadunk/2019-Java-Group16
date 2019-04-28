package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.house.AStarNode;
import com.doogies.savepups.house.HouseGraph;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PathfindingDemoState extends State{

    private HouseGraph house;

    public PathfindingDemoState(Handler handler) {
        super(handler);
        house = new HouseGraph(handler);
        handler.setRoom(house.getRoom(0), 4 * Tile.TILEWIDTH, 5*Tile.TILEHEIGHT);
    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
            State.setState(handler.getGame().pause);
        }

        handler.getRoom().tick();

    }

    @Override
    public void render(Graphics g) {
        handler.getRoom().render(g);
    }

    @Override
    public void startMusic() {

    }

    @Override
    public void stopMusic() {

    }
}
