package com.doogies.savepups.house;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.EntityManager;
import com.doogies.savepups.entities.creatures.Enemies.EnemyManager;
import com.doogies.savepups.hud.GameHud;
import com.doogies.savepups.items.ItemManager;

import com.doogies.savepups.entities.statics.StaticsManager;
import com.doogies.savepups.tiles.Tile;
import com.doogies.savepups.utils.Utils;
import java.awt.*;
import java.util.ArrayList;

public class Room {

    private int width, height;
    private int spawnX, spawnY;
    public int ID;

    private int furnitureId;
    private int furnX;
    private int furnY;

    private int enemyId;
    private int enemyX, enemyY;

    private String roomPath;

    private StaticsManager furniture;
    private EnemyManager enemies;
    private AStarPathFinder pathFinder;
    private Handler handler;

    // Entities
    private EntityManager entityManager;

    // Item
    private ItemManager itemManager;

    // GameHud
    private GameHud gameHud;


    public Room(Handler handler, String roomFile, int ID) {
        this.ID = ID;
        this.handler = handler;
        this.roomPath = "res/rooms/" + roomFile;
        setupManagers();
        loadRoom(roomPath);
    }

    private void setupManagers() {
        this.furniture = new StaticsManager(handler);
        this.enemies = new EnemyManager(handler);
        this.entityManager = new EntityManager(handler, handler.getPlayer());
        this.itemManager = new ItemManager(handler);
        this.gameHud = new GameHud(handler);
    }

    public void tick() {
        itemManager.tick();
        entityManager.tick();
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
                pathFinder.getNode(x, y).renderAStarNode(g,
                        (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        // Item
        itemManager.render(g);

        // Entities
        entityManager.render(g);
    }

    private void loadRoom(String path) {
        String file = Utils.loadFileAsString(path);
        String[] roomAttributes = file.split((","));
        getRoomDimensions(roomAttributes[0]);
        getRoomPlayerSpawn(roomAttributes[1]);
        getRoomTiles(roomAttributes[2]);
        loadFurniture(roomAttributes[3]);
        loadEnemies(roomAttributes[4]);
    }

    public void getRoomDimensions(String roomDimensions){
        String[] tokens = roomDimensions.split(("\\s+"));
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
    }

    public void getRoomPlayerSpawn(String spawnValues){
        String[] tokens = spawnValues.split(("\\s+"));
        spawnX = Utils.parseInt(tokens[1]) * Tile.TILEWIDTH;
        spawnY = Utils.parseInt(tokens[2]) * Tile.TILEHEIGHT;
    }

    public void getRoomTiles(String tilePositions) {
        String[] tokens = tilePositions.split(("\\s+"));
        pathFinder = new AStarPathFinder(width, height, handler);
        int tileToken;

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++) {
                tileToken = Utils.parseInt(tokens[(x + y * width) + 1]);

                if (tileToken > 99) {
                    AStarNode node = new AStarNode(x, y, Tile.tiles[3].getTexture(), Tile.tiles[3].isSolid(), handler);
                    node.setEntry(true);
                    node.worldID = tileToken % 100;
                    HouseGraph.addRoomEntrance(ID, node.worldID, node);
                    pathFinder.addNode(x, y, node);
                } else {
                    AStarNode node = new AStarNode(x, y, Tile.tiles[tileToken].getTexture(), Tile.tiles[tileToken].isSolid(), handler);
                    pathFinder.addNode(x, y, node);
                }

            }
        }

        pathFinder.makeNeighboursForNodes();
        setWorldTileSpawns();
    }

    public void setWorldTileSpawns() {
        ArrayList<AStarNode> nodes = HouseGraph.house.get(ID);

        for(AStarNode node: nodes) {
            for(AStarNode nodeNeighbour : node.getHorizontalVerticalNeighbours()) {
                if(!nodeNeighbour.isSolid) {
                    node.roomSpawnX = nodeNeighbour.x * Tile.TILEWIDTH;
                    node.roomSpawnY = nodeNeighbour.y * Tile.TILEHEIGHT;
                }
            }
        }
    }

    public void loadFurniture(String furnitureSpawns) {

        String[] tokens = furnitureSpawns.split(("\\s+"));

        if(tokens.length < 2) return;

        for(int i = 1; i < tokens.length; i = i + 3) {
            furnitureId = Utils.parseInt(tokens[i]);
            furnX = Utils.parseInt(tokens[i + 1]) * Tile.TILEWIDTH;
            furnY = Utils.parseInt(tokens[i + 2]) * Tile.TILEHEIGHT;

            furniture.insertStatics(entityManager, furnitureId, furnX, furnY);
        }
    }

    public void loadEnemies(String enemySpawns) {

        String[] tokens = enemySpawns.split(("\\s+"));

        if(tokens.length < 2) return;

        for(int i = 1; i < tokens.length; i = i + 3) {
            enemyId = Utils.parseInt(tokens[i]);
            enemyX = Utils.parseInt(tokens[i + 1]) * Tile.TILEWIDTH;
            enemyY = Utils.parseInt(tokens[i + 2]) * Tile.TILEHEIGHT;

            enemies.insertEnemies(entityManager, enemyId, enemyX, enemyY);
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

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void newEntityManager() {
        entityManager = new EntityManager(handler, handler.getPlayer());
    }

    public AStarPathFinder getPathFinder() { return pathFinder; }

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


}
