package com.doogies.savepups.graphics;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    //public static BufferedImage computer, bed, wall, roof, floor, damagedFloor, door;
    public static BufferedImage player;

    public static BufferedImage pinkFloor, brickWall, doorwayWall, damagedFloor;

    public static void init() {
        SpriteSheet mapsheet = new SpriteSheet(ImageLoader.loadImage("/textures/mapdata.png"));
        SpriteSheet playersheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet tileSpritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/tileSpritesheet.png"));


        // ENVIRONMENT
//        wall = mapsheet.crop(width * 9, height + 13, width, height + 2);
//        roof = mapsheet.crop(width * 9, 16, width, height - 2);
//        computer = mapsheet.crop(0, 0, width, height);
//        bed = mapsheet.crop(width, 0, width, height + 16);
//        floor = mapsheet.crop(width * 6, 0, width / 2, height / 2);
//        damagedFloor = mapsheet.crop(width * 7, 0, width / 2, height / 2);
//        door = mapsheet.crop(237, 16, 21, 31);

        pinkFloor = tileSpritesheet.crop((4 * 64) - 64,(1 * 64) - 64, 64, 64);
        brickWall= tileSpritesheet.crop((2 * 64) - 64,(1 * 64) - 64, 64, 64);
        doorwayWall= tileSpritesheet.crop((3 * 64) - 64,(1 * 64) - 64, 64, 64);
        damagedFloor= tileSpritesheet.crop((1 * 64) - 64,(2 * 64) - 64, 64, 64);

        // ENTITIES
        player = playersheet.crop(0, 0, width, height);
    }
}
