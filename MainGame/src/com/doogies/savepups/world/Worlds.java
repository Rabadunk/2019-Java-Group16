package com.doogies.savepups.world;

import com.doogies.savepups.Game;
import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.EntityManager;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.entities.statics.Bed;
import com.doogies.savepups.tiles.Tile;
import com.doogies.savepups.utils.Utils;

import java.awt.*;

public class Worlds {

    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private Handler handler;

    //Entities
    private EntityManager entityManager;

    public Worlds(Handler handler, String path) {
        this.handler = handler;
        //Code for correct map pos from prev gamestate function
        // player = new Player(handler,(world.getSpawnX()-1) * 64, (world.getSpawnY()-1) * 64);
        entityManager = new EntityManager(handler, new Player(handler, 500,500));
        entityManager.addEntity(new Bed(handler, 100, 150));
       // entityManager.addEntity(new Bed(handler, 100, 250));
        //entityManager.addEntity(new Bed(handler, 100, 350));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX * Tile.TILEWIDTH);
        entityManager.getPlayer().setY(spawnY * Tile.TILEHEIGHT);
    }



    public void tick() {
        entityManager.tick();
    }

    public void render(Graphics g) {
        //Render effiency. Sets values so that only tiles that can seen on screen are rendered.
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        //Render loop
        for(int y = yStart; y < yEnd; y++) {
            for(int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g,
                        (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        //Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.pinkFloorTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null) {
            return Tile.brickWallTile;
        }

        return t;
    }


    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split(("\\s+"));
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
