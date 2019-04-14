package com.doogies.savepups.graphics;

import java.awt.image.BufferedImage;
import com.doogies.savepups.tiles.Tile;

public class Assets {

    private static final int width = 32, height = 32;

    //public static BufferedImage computer, bed, wall, roof, floor, damagedFloor, door;

    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage playerIdle;

    public static BufferedImage pinkFloor, brickWall, doorwayWall, damagedFloor;

    // variables to make tileset calcs cleaner
    private static final int tileHeight = Tile.TILEHEIGHT;
    private static final int tileWidth = Tile.TILEHEIGHT;


    public static void init() {
        SpriteSheet mapsheet = new SpriteSheet(ImageLoader.loadImage("/textures/mapdata.png"));
        SpriteSheet playersheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet tileSpritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/tileSpritesheet.png"));

        //Player animations
        player_down = new BufferedImage[2];
        player_down[0] = playersheet.crop(0,0, width, height);
        player_down[1] = playersheet.crop(64,0, width, height);

        player_up = new BufferedImage[2];
        player_up[0] = playersheet.crop(0,96, width, height);
        player_up[1] = playersheet.crop(64,96, width, height);

        player_left = new BufferedImage[2];
        player_left[0] = playersheet.crop(0,32, width, height);
        player_left[1] = playersheet.crop(64,32, width, height);

        player_right = new BufferedImage[2];
        player_right[0] = playersheet.crop(0,64, width, height);
        player_right[1] = playersheet.crop(64,64, width, height);

        playerIdle = playersheet.crop(32, 0, width, height);


        // ENVIRONMENT
//        roof = mapsheet.crop(width * 9, 16, width, height - 2);
//        computer = mapsheet.crop(0, 0, width, height);
//        bed = mapsheet.crop(width, 0, width, height + 16);
//        door = mapsheet.crop(237, 16, 21, 31);

        // Tileset Spritesheet
        // Crops the texture from the spritesheet. Tiles are always 64x64 pixels in this sprite sheet
        // So we can use an row/collumn * 64 to quickly obtain the starting pos of the sprite.
        pinkFloor = tileSpritesheet.crop((4 -1)* tileWidth,(1 - 1) * tileHeight, tileWidth, tileHeight);
        brickWall= tileSpritesheet.crop((2 - 1)* tileWidth,(1 - 1)* tileHeight, tileWidth, tileHeight);
        doorwayWall= tileSpritesheet.crop((3 - 1)* tileWidth,(1 - 1) * tileHeight, tileWidth, tileHeight);
        damagedFloor= tileSpritesheet.crop((1 - 1) * tileWidth,(2 - 1)* tileHeight, tileWidth, tileHeight);

        // ENTITIES
        //player = playersheet.crop(32, 0, width, height);
    }
}
