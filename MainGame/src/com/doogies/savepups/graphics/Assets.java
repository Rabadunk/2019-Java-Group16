package com.doogies.savepups.graphics;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage computer, bed;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/mapdata.png"));
        computer = sheet.crop(0, 0, width, height);
        bed = sheet.crop(width, 0, width, height + 16);
    }
}
