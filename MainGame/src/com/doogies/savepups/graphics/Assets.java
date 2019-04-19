package com.doogies.savepups.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.doogies.savepups.tiles.Tile;

public class Assets {

    private static final int width = 32, height = 32;

    public static Font font28;

    //public static BufferedImage computer, bed, wall, roof, floor, damagedFloor, door;

    public static BufferedImage computer, bed;

    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage playerIdleDown, playerIdleUp, playerIdleLeft, playerIdleRight;
    public static BufferedImage attack;

    public static BufferedImage pinkFloor, brickWall, doorwayWall, damagedFloor;

    // ui
    public static BufferedImage[] playButton, scoreButton, quitButton;
    public static BufferedImage inventoryScreen;

    // variables to make tileset calcs cleaner
    private static final int tileHeight = Tile.TILEHEIGHT;
    private static final int tileWidth = Tile.TILEHEIGHT;

    public static void init() {
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);

        SpriteSheet mapsheet = new SpriteSheet(ImageLoader.loadImage("/textures/mapdata.png"));
        SpriteSheet playersheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet tileSpritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/tileSpritesheet.png"));
        SpriteSheet button = new SpriteSheet(ImageLoader.loadImage("/ui/buttons.png"));
        SpriteSheet attackSprites = new SpriteSheet(ImageLoader.loadImage("/textures/attack.png"));

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

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

        playerIdleDown = playersheet.crop(32, 0, width, height);
        playerIdleUp = playersheet.crop(32, 96, width, height);
        playerIdleLeft = playersheet.crop(32, 32, width, height);
        playerIdleRight = playersheet.crop(32, 64, width, height);

        attack = attackSprites.crop(30, 690, 200, 130);

        // ENVIRONMENT
//        roof = mapsheet.crop(width * 9, 16, width, height - 2);
        computer = mapsheet.crop(0, 0, width, height);
        bed = mapsheet.crop(width, 0, width, height + 16);
//        door = mapsheet.crop(237, 16, 21, 31);

        // Tileset Spritesheet
        // Crops the texture from the spritesheet. Tiles are always 64x64 pixels in this sprite sheet
        // So we can use an row/column * 64 to quickly obtain the starting position of the sprite.
        pinkFloor = tileSpritesheet.crop((4 -1)* tileWidth,(1 - 1) * tileHeight, tileWidth, tileHeight);
        brickWall= tileSpritesheet.crop((2 - 1)* tileWidth,(1 - 1)* tileHeight, tileWidth, tileHeight);
        doorwayWall= tileSpritesheet.crop((3 - 1)* tileWidth,(1 - 1) * tileHeight, tileWidth, tileHeight);
        damagedFloor= tileSpritesheet.crop((1 - 1) * tileWidth,(2 - 1)* tileHeight, tileWidth, tileHeight);

        // UI
        playButton = new BufferedImage[2];
        playButton[0] = button.crop(0, 0, 300, 150); // Not selected
        playButton[1] = button.crop(300, 0, 300, 150);

        quitButton = new BufferedImage[2];
        quitButton[0] = button.crop(0, 150, 300, 150); // Not selected
        quitButton[1] = button.crop(300, 150, 300, 150);

        scoreButton = new BufferedImage[2];
        scoreButton[0] = button.crop(0, 300, 300, 150); // Not selected
        scoreButton[1] = button.crop(300, 300, 300, 150);
    }
}
