package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.house.AStarNode;
import com.doogies.savepups.house.HouseGraph;
import com.doogies.savepups.house.Room;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

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

        checkForRoomChange();

        handler.getRoom().tick();

    }

    private void checkForRoomChange() {
        Player player = handler.getPlayer();
        Room room = house.getRoom(player.getTileWorldID());
        if(player.inEntry()) {
            ArrayList<AStarNode> houseGraph = HouseGraph.house.get(room.ID);
            AStarNode entrance = houseGraph.get(handler.getRoom().ID);
            handler.setRoom(room, entrance.roomSpawnX, entrance.roomSpawnY);
        }
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
