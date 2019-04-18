package com.doogies.savepups.states;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.house.HouseGraph;
import com.doogies.savepups.house.Room;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameState extends State {

    private HouseGraph house;
    private ArrayList<Room> rooms;

    private boolean worldChanged = false;

    public GameState(Handler handler){
        super(handler);
        house = new HouseGraph(handler);
        handler.setRoom(house.getRoom(1));
    }

    @Override
    public void tick() {
        checkForRoomChange();
        handler.getRoom().tick();
    }

    private void checkForRoomChange() {
        Player player = handler.getRoom().getEntityManager().getPlayer();
        Room room = house.getRoom(player.getTileWorldID());

        if(player.inEntry()) {
            handler.setRoom(room);
            worldChanged = true;
            handler.getRoom().getEntityManager().getPlayer().setX(room.getSpawnX());
            handler.getRoom().getEntityManager().getPlayer().setY(room.getSpawnY());
        }
    }

    @Override
    public void render(Graphics g) {
        if(worldChanged) {
            loadScreen(g);
        }
        handler.getRoom().render(g);
    }

    private void loadScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0 ,0, handler.getWidth(), handler.getHeight());

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worldChanged = false;
    }

}
