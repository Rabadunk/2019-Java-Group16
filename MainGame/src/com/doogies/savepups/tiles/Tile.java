package com.doogies.savepups.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    // STATIC STUFF HERE

    public static Tile[] tiles = new Tile[256];
    public static Tile pinkFloorTile = new PinkFloorTile(0);
    public static Tile damagedFloorTile = new DamagedFloorTile(1);
    public static Tile brickWallTile = new BrickWallTile(2);
    public static Tile doorwayWallTile = new DoorwayWallTile(3);


    // CLASS

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;
    protected int worldId;
    protected boolean entry = false;



    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }

    public int getId() {
        return id;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public boolean isEntry() { return entry; }

    public void setWorldId(int id) {
        worldId = id;
    }

    public int getWorldId() {
        return worldId;
    }

    public BufferedImage getTexture() {
        return texture;
    }


    public void setEntry(boolean b) {
        entry = b;
    }
}
