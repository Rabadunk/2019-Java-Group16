package com.doogies.savepups.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    // STATIC STUFF HERE

    public static Tile[] tiles = new Tile[256];
    public static Tile floorTile = new FloorTile(0);
    public static Tile damagedFloorTile = new DamagedFloorTile(1);
    public static Tile wallTile = new WallTile(2);
    public static Tile doorTile = new DoorTile(3);


    // CLASS

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;



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


}