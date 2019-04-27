package com.doogies.savepups.graphics.assets;

import java.awt.image.BufferedImage;

import com.doogies.savepups.graphics.ImageLoader;
import com.doogies.savepups.graphics.SpriteSheet;
import com.doogies.savepups.tiles.Tile;

public class FurnitureAssets {

    private static SpriteSheet furnitureSheet;
    private static final int tileWidth = Tile.TILEWIDTH, tileHeight = Tile.TILEHEIGHT;

    public static BufferedImage computer, bed, potPlant;

    public static void init() {
        furnitureSheet = new SpriteSheet(ImageLoader.loadImage("/textures/furnitureSpritesheet.png"));

        bed = furnitureSheet.crop(0, 0, tileWidth, tileHeight * 2);
        potPlant = furnitureSheet.crop(tileWidth, 0, tileWidth, tileHeight);
        computer = furnitureSheet.crop(tileWidth*2, 0, tileWidth*2, tileHeight);
    }

}
