package com.doogies.savepups.graphics;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage computer, bed, wall, roof, floor, damagedFloor;
    public static BufferedImage player;

    public static void init() {
        SpriteSheet mapsheet = new SpriteSheet(ImageLoader.loadImage("/textures/mapdata.png"));
        SpriteSheet playersheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));


        // ENVIRONMENT
        wall = mapsheet.crop(width * 9, height + 13, width, height + 2);
        roof = mapsheet.crop(width * 9, 16, width, height - 2);
        computer = mapsheet.crop(0, 0, width, height);
        bed = mapsheet.crop(width, 0, width, height + 16);
        floor = mapsheet.crop(width * 6, 0, width / 2, height / 2);
        damagedFloor = mapsheet.crop(width * 7, 0, width / 2, height / 2);

        // ENTITIES
        player = playersheet.crop(0, 0, width, height);
    }
}
