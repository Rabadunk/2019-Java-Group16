package com.doogies.savepups.graphics.assets;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.doogies.savepups.graphics.ImageLoader;
import com.doogies.savepups.graphics.SpriteSheet;
import com.doogies.savepups.tiles.Tile;

public class TileAssets {

    public static BufferedImage pinkFloor, brickWall, doorwayWall, damagedFloor;
    public static BufferedImage pinkCarpetTopLeft, pinkCarpetTop, pinkCarpetTopRight, pinkCarpetLeft, pinkCarpetCenter, pinkCarpetRight, pinkCarpetBottomLeft, pinkCarpetBottom, pinkCarpetBottomRight;
    public static BufferedImage window;
    private static final int tileWidth = Tile.TILEWIDTH, tileHeight = Tile.TILEHEIGHT;
    private static SpriteSheet tileSpritesheet;

    public static void init() {
        tileSpritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/tileSpritesheet.png"));

        loadPinkCarpet();

        // Tileset Spritesheet
        // Crops the texture from the spritesheet. Tiles are always 64x64 pixels in this sprite sheet
        // So we can use an row/column * 64 to quickly obtain the starting position of the sprite.
        brickWall = tileSpritesheet.crop(tileWidth,0, tileWidth, tileHeight);
        pinkFloor = tileSpritesheet.crop((4 -1)* tileWidth,0, tileWidth, tileHeight);
        doorwayWall = tileSpritesheet.crop((3 - 1)* tileWidth,0, tileWidth, tileHeight);
        damagedFloor = tileSpritesheet.crop((1 - 1) * tileWidth,0, tileWidth, tileHeight);
        window = tileSpritesheet.crop(2*tileWidth, tileHeight, tileWidth, tileHeight);
    }

    private static void loadPinkCarpet() {
        pinkCarpetTopLeft = tileSpritesheet.crop(0, tileHeight * 2, tileWidth, tileHeight);
        pinkCarpetTop = tileSpritesheet.crop(tileWidth, tileHeight * 2, tileWidth, tileHeight);
        pinkCarpetTopRight = tileSpritesheet.crop(2 * tileWidth, tileHeight * 2, tileWidth, tileHeight);
        pinkCarpetLeft = tileSpritesheet.crop(0, tileHeight * 3, tileWidth, tileHeight);
        pinkCarpetCenter = tileSpritesheet.crop(tileWidth, tileHeight * 3, tileWidth, tileHeight);
        pinkCarpetRight = tileSpritesheet.crop(2 * tileWidth, tileHeight * 3, tileWidth, tileHeight);
        pinkCarpetBottomLeft = tileSpritesheet.crop(0, tileHeight * 4, tileWidth, tileHeight);
        pinkCarpetBottom = tileSpritesheet.crop(tileWidth, tileHeight * 4, tileWidth, tileHeight);
        pinkCarpetBottomRight = tileSpritesheet.crop(2*tileWidth, tileHeight * 4, tileWidth, tileHeight);
    }
}
