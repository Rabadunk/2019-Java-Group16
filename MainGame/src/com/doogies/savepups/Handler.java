package com.doogies.savepups;

import com.doogies.savepups.graphics.GameCamera;
import com.doogies.savepups.input.KeyManager;
import com.doogies.savepups.world.Worlds;

public class Handler {

    private Game game;
    private Worlds world;

    public Handler(Game game) {
        this.game = game;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Worlds getWorld() {
        return world;
    }

    public void setWorld(Worlds world) {
        this.world = world;
    }
}
