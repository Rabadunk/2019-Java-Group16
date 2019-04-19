package com.doogies.savepups.house;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.EntityManager;
import com.doogies.savepups.entities.furniture.FurnitureManager;
import com.doogies.savepups.tiles.Tile;
import com.doogies.savepups.utils.Utils;

import java.awt.*;

public class Room {

    private int width, height;
    private int spawnX, spawnY;
    private int ID;
    private int furnitureId;
    private int furnX;
    private int furnY;

    private String furniturePath;
    private Tile[][] tiles;
    private FurnitureManager furniture;
    private Handler handler;


    public Room(Handler handler, String roomPath, String furniturePath, int ID) {
        this.ID = ID;
        this.handler = handler;
        this.furniturePath = furniturePath;
        this.furniture = new FurnitureManager(handler);
        //Code for correct map pos from prev gamestate function
        // player = new Player(handler,(house.getSpawnX()-1) * 64, (house.getSpawnY()-1) * 64);
       // entityManager.addEntity(new Bed(handler, 100, 250));
        //entityManager.addEntity(new Bed(handler, 100, 350));
        loadRoom(roomPath);

        handler.entityManager.getPlayer().setX(spawnX);
        handler.entityManager.getPlayer().setY(spawnY);
    }



    public void tick() {
        handler.entityManager.tick();
    }

    public void render(Graphics g) {
        // Render black backdrop
        g.setColor(Color.black);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());


        // Set values so that only tiles that can seen on screen are rendered.
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
        handler.entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        try {
            return tiles[x][y];
        } catch(Exception e) {
            return Tile.pinkFloorTile;
        }
    }


    private void loadRoom(String path) {
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

        System.out.println(spawnX + " " + spawnY);

    }

    public void loadFurniture() {
        String file = Utils.loadFileAsString(furniturePath);
        String[] tokens = file.split(("\\s+"));

        for(int i = 0; i < tokens.length; i = i + 3) {
            furnitureId = Utils.parseInt(tokens[i]);
            furnX = Utils.parseInt(tokens[i + 1]) * Tile.TILEWIDTH;
            furnY = Utils.parseInt(tokens[i + 2]) * Tile.TILEHEIGHT;

            furniture.insertFurniture(getEntityManager(), furnitureId, furnX, furnY);
        }
    }

    public int getID(){return  ID; }

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
        return handler.entityManager;
    }
}
