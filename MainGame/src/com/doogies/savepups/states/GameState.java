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
    private Room currentRoom;

    public GameState(Handler handler){
        super(handler);
        house = new HouseGraph(handler);
        handler.setRoom(house.getRoom(0));

        currentRoom = handler.getRoom();
        handler.getPlayer().setX(currentRoom.getSpawnX());
        handler.getPlayer().setY(currentRoom.getSpawnY());
    }

    @Override
    public void tick() {
        checkForRoomChange();
        handler.getRoom().tick();
    }

    private void checkForRoomChange() {
        Player player = handler.getPlayer();
        Room room = house.getRoom(player.getTileWorldID());

        if(player.inEntry()) {
            currentRoom = room;
            handler.setRoom(room);
            worldChanged = true;
        }
    }

    @Override
    public void render(Graphics g) {
        if(worldChanged) {
            loadScreen(g);
        }
        handler.getRoom().render(g);
    }

    @Override
    public void startMusic() {

    }

    @Override
    public void stopMusic() {

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
