package com.doogies.savepups;

import com.doogies.savepups.graphics.GameCamera;
import com.doogies.savepups.house.Room;
import com.doogies.savepups.input.KeyManager;
import com.doogies.savepups.input.MouseManager;

public class Handler {

    private Game game;
    private Room room;

    public Handler(Game game) {
        this.game = game;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() { return  game.getMouseManager(); }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() { return game.getHeight(); }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
