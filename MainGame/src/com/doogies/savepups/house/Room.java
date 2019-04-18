package com.doogies.savepups.house;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.EntityManager;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.entities.statics.Bed;
import com.doogies.savepups.items.ItemManager;
import com.doogies.savepups.tiles.Tile;
import com.doogies.savepups.utils.Utils;

import java.awt.*;

public class Room {

    private int width, height;
    private int spawnX, spawnY;
    private int ID;
    private Tile[][] tiles;
    private Handler handler;

    // Entities
    private EntityManager entityManager;

    // Item
    private ItemManager itemManager;

    public Room(Handler handler, String path, int ID) {
        this.ID = ID;
        this.handler = handler;

        //Code for correct map pos from prev gamestate function
        // player = new Player(handler,(house.getSpawnX()-1) * 64, (house.getSpawnY()-1) * 64);
        entityManager = new EntityManager(handler, new Player(handler, 500,500));
        itemManager = new ItemManager(handler);

        // Temp entity spawn
        entityManager.addEntity(new Bed(handler, 100, 150));
       // entityManager.addEntity(new Bed(handler, 100, 250));
        //entityManager.addEntity(new Bed(handler, 100, 350));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }



    public void tick() {
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g) {
        // Render black backdrop
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());


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

        // Item
        itemManager.render(g);

        // Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        try {
            return tiles[x][y];
        } catch(Exception e) {
            return Tile.pinkFloorTile;
        }
    }


    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split(("\\s+"));
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]) * Tile.TILEWIDTH;
        spawnY = Utils.parseInt(tokens[3]) * Tile.TILEHEIGHT;

        tiles = new Tile[width][height];
        int tileID;

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                tileID = Utils.parseInt(tokens[(x + y * width) + 4]);
                Tile newTile;

                if(tileID > 99) {
                    newTile = new Tile(Tile.tiles[3].getTexture(), tileID);
                    newTile.setWorldId(tileID % 100);
                    newTile.setEntry(true);
                } else {
                    newTile = Tile.tiles[tileID];
                }
                tiles[x][y] = newTile;
            }
        }


    }

    // Getters and setters


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public int getID(){return  ID;}

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
