package com.doogies.savepups.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.doogies.savepups.tiles.Tile;

public class Assets {

    private static final int width = 32, height = 32;

    public static Font font28, fontTitle, fontHud;

    //public static BufferedImage computer, bed, wall, roof, floor, damagedFloor, door;

    public static BufferedImage computer, bed;

    public static BufferedImage doogie;

    public static BufferedImage barrel1, barrel2;

    public static BufferedImage[] player_left, player_right;
    public static BufferedImage playerIdleLeft, playerIdleRight;

    public static BufferedImage[] enemy_down, enemy_up, enemy_left, enemy_right;
    public static BufferedImage enemyIdleDown, enemyIdleUp, enemyIdleLeft, enemyIdleRight;

    public static BufferedImage[] ogre_right, ogre_left;
    public static BufferedImage ogreIdleRight, ogreIdleLeft;

    public static BufferedImage[] orc_right, orc_left;
    public static BufferedImage orcIdleRight, orcIdleLeft;

    public static BufferedImage[] orphan_right, orphan_left;
    public static BufferedImage orphanIdleRight, orphanIdleLeft;

    public static BufferedImage[] screamer_right, screamer_left;
    public static BufferedImage screamerIdleRight, screamerIdleLeft;

    public static BufferedImage attack;

    public static BufferedImage pinkFloor, brickWall, doorwayWall, damagedFloor;

    public static BufferedImage sword;

    // ui
    public static BufferedImage[] playButton, scoreButton, quitButton;
    public static BufferedImage inventoryScreen;

    // variables to make tileset calcs cleaner
    private static final int tileHeight = Tile.TILEHEIGHT;
    private static final int tileWidth = Tile.TILEHEIGHT;

    //

    public static void init() {
        font28 = FontLoader.loadFont("res/fonts/slkscr/slkscr.ttf", 28);
        fontTitle = FontLoader.loadFont("res/fonts/Pacifico/Pacifico.ttf", 76);
        fontHud = FontLoader.loadFont("res/fonts/ArmWrestler/ArmWrestler.ttf", 28);


        SpriteSheet mapsheet = new SpriteSheet(ImageLoader.loadImage("/textures/mapdata.png"));
        SpriteSheet playersheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet enemysheet = new SpriteSheet(ImageLoader.loadImage("/textures/enemy.png"));
        SpriteSheet tileSpritesheet = new SpriteSheet(ImageLoader.loadImage("/textures/tileSpritesheet.png"));
        SpriteSheet button = new SpriteSheet(ImageLoader.loadImage("/ui/buttons.png"));
        SpriteSheet attackSprites = new SpriteSheet(ImageLoader.loadImage("/textures/attack.png"));
        SpriteSheet itemSprites = new SpriteSheet(ImageLoader.loadImage("/textures/items.png"));
        SpriteSheet ogreSprites = new SpriteSheet(ImageLoader.loadImage("/textures/ogre.png"));
        SpriteSheet screamerSprites = new SpriteSheet(ImageLoader.loadImage("/textures/screamer.png"));
        SpriteSheet orcSprites = new SpriteSheet(ImageLoader.loadImage("/textures/orc.png"));
        SpriteSheet orphanSprites = new SpriteSheet(ImageLoader.loadImage("/textures/orphan.png"));
        SpriteSheet barrelSprites = new SpriteSheet(ImageLoader.loadImage("/textures/barrels/barrels.png"));

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

        // Player animations
        int halfWidth = width/2;
        int halfHeight = height;
        player_right = new BufferedImage[8];
        player_right[0] = playersheet.crop(0,0, halfWidth, height);
        player_right[1] = playersheet.crop(halfWidth,0, halfWidth, height);
        player_right[2] = playersheet.crop(2*halfWidth,0, halfWidth, height);
        player_right[3] = playersheet.crop(3*halfWidth,0, halfWidth, height);
        player_right[4] = playersheet.crop(4*halfWidth,0, halfWidth, height);
        player_right[5] = playersheet.crop(5*halfWidth,0, halfWidth, height);
        player_right[6] = playersheet.crop(6*halfWidth,0, halfWidth, height);
        player_right[7] = playersheet.crop(7*halfWidth,0, halfWidth, height);

        player_left = new BufferedImage[8];
        player_left[0] = playersheet.crop(halfWidth,halfHeight, halfWidth, height);
        player_left[1] = playersheet.crop(2*halfWidth,halfHeight, halfWidth, height);
        player_left[2] = playersheet.crop(3*halfWidth,halfHeight, halfWidth, height);
        player_left[3] = playersheet.crop(4*halfWidth,halfHeight, halfWidth, height);
        player_left[4] = playersheet.crop(5*halfWidth,halfHeight, halfWidth, height);
        player_left[5] = playersheet.crop(6*halfWidth,halfHeight, halfWidth, height);
        player_left[6] = playersheet.crop(7*halfWidth,halfHeight, halfWidth, height);
        player_left[7] = playersheet.crop(8*halfWidth,halfHeight, halfWidth, height);



        playerIdleLeft = player_left[7];
        playerIdleRight = player_right[0];
        doogie = ImageLoader.loadImage("/textures/doogies/doogie.png");


        attack = attackSprites.crop(30, 690, 200, 130);

        // Ogre Animations
        enemy_down = new BufferedImage[2];
        enemy_down[0] = enemysheet.crop(0,0, width, height);
        enemy_down[1] = enemysheet.crop(64,0, width, height);

        enemy_up = new BufferedImage[2];
        enemy_up[0] = enemysheet.crop(0,96, width, height);
        enemy_up[1] = enemysheet.crop(64,96, width, height);

        enemy_right = new BufferedImage[2];
        enemy_right[0] = enemysheet.crop(0,32, width, height);
        enemy_right[1] = enemysheet.crop(64,32, width, height);

        enemy_left = new BufferedImage[2];
        enemy_left[0] = enemysheet.crop(0,64, width, height);
        enemy_left[1] = enemysheet.crop(64,64, width, height);

        enemyIdleDown = enemysheet.crop(32, 0, width, height);
        enemyIdleUp = enemysheet.crop(32, 96, width, height);
        enemyIdleRight = enemysheet.crop(32, 32, width, height);
        enemyIdleLeft = enemysheet.crop(32, 64, width, height);

        // Ogre animation
        ogre_right = new BufferedImage[8];
        ogre_right[0] = ogreSprites.crop(0, 0, width, height);
        ogre_right[1] = ogreSprites.crop(width, 0, width, height);
        ogre_right[2] = ogreSprites.crop(width * 2, 0, width, height);
        ogre_right[3] = ogreSprites.crop(width * 3, 0, width, height);
        ogre_right[4] = ogreSprites.crop(width * 4, 0, width, height);
        ogre_right[5] = ogreSprites.crop(width * 5, 0, width, height);
        ogre_right[6] = ogreSprites.crop(width * 6, 0, width, height);
        ogre_right[7] = ogreSprites.crop(width * 7, 0, width, height);


        ogre_left = new BufferedImage[8];
        ogre_left[0] = ogreSprites.crop(0, height, width, height);
        ogre_left[1] = ogreSprites.crop(width, height, width, height);
        ogre_left[2] = ogreSprites.crop(width * 2, height, width, height);
        ogre_left[3] = ogreSprites.crop(width * 3, height, width, height);
        ogre_left[4] = ogreSprites.crop(width * 4, height, width, height);
        ogre_left[5] = ogreSprites.crop(width * 5, height, width, height);
        ogre_left[6] = ogreSprites.crop(width * 6, height, width, height);
        ogre_left[7] = ogreSprites.crop(width * 7, height, width, height);

        ogreIdleLeft = ogreSprites.crop(0, 0, width, height);
        ogreIdleRight = ogreSprites.crop(width * 7, height, width, height);

        // Screamer animation
        screamer_right = new BufferedImage[8];
        screamer_right[0] = screamerSprites.crop(0, 0, width, height);
        screamer_right[1] = screamerSprites.crop(width, 0, width, height);
        screamer_right[2] = screamerSprites.crop(width * 2, 0, width, height);
        screamer_right[3] = screamerSprites.crop(width * 3, 0, width, height);
        screamer_right[4] = screamerSprites.crop(width * 4, 0, width, height);
        screamer_right[5] = screamerSprites.crop(width * 5, 0, width, height);
        screamer_right[6] = screamerSprites.crop(width * 6, 0, width, height);
        screamer_right[7] = screamerSprites.crop(width * 7, 0, width, height);


        screamer_left = new BufferedImage[8];
        screamer_left[0] = screamerSprites.crop(0, height, width, height);
        screamer_left[1] = screamerSprites.crop(width, height, width, height);
        screamer_left[2] = screamerSprites.crop(width * 2, height, width, height);
        screamer_left[3] = screamerSprites.crop(width * 3, height, width, height);
        screamer_left[4] = screamerSprites.crop(width * 4, height, width, height);
        screamer_left[5] = screamerSprites.crop(width * 5, height, width, height);
        screamer_left[6] = screamerSprites.crop(width * 6, height, width, height);
        screamer_left[7] = screamerSprites.crop(width * 7, height, width, height);

        screamerIdleLeft = screamerSprites.crop(0, 0, width, height);
        screamerIdleRight = screamerSprites.crop(width * 7, height, width, height);

        // Orc animation
        orc_right = new BufferedImage[8];
        orc_right[0] = orcSprites.crop(0, 0, width, height);
        orc_right[1] = orcSprites.crop(width, 0, width, height);
        orc_right[2] = orcSprites.crop(width * 2, 0, width, height);
        orc_right[3] = orcSprites.crop(width * 3, 0, width, height);
        orc_right[4] = orcSprites.crop(width * 4, 0, width, height);
        orc_right[5] = orcSprites.crop(width * 5, 0, width, height);
        orc_right[6] = orcSprites.crop(width * 6, 0, width, height);
        orc_right[7] = orcSprites.crop(width * 7, 0, width, height);


        orc_left = new BufferedImage[8];
        orc_left[0] = orcSprites.crop(0, height, width, height);
        orc_left[1] = orcSprites.crop(width, height, width, height);
        orc_left[2] = orcSprites.crop(width * 2, height, width, height);
        orc_left[3] = orcSprites.crop(width * 3, height, width, height);
        orc_left[4] = orcSprites.crop(width * 4, height, width, height);
        orc_left[5] = orcSprites.crop(width * 5, height, width, height);
        orc_left[6] = orcSprites.crop(width * 6, height, width, height);
        orc_left[7] = orcSprites.crop(width * 7, height, width, height);

        orcIdleLeft = orcSprites.crop(0, 0, width, height);
        orcIdleRight = orcSprites.crop(width * 7, height, width, height);

        // orphan
        orphan_right = new BufferedImage[4];
        orphan_right[0] = orphanSprites.crop(0, 0, width/2, height/2);
        orphan_right[1] = orphanSprites.crop(16, 0, width/2, height/2);
        orphan_right[2] = orphanSprites.crop(32, 0, width/2, height/2);
        orphan_right[3] = orphanSprites.crop(48, 0, width/2, height/2);

        orphan_left = new BufferedImage[4];
        orphan_left[0] = orphanSprites.crop(0, 16, width/2, height/2);
        orphan_left[1] = orphanSprites.crop(16, 16, width/2, height/2);
        orphan_left[2] = orphanSprites.crop(32, 16, width/2, height/2);
        orphan_left[3] = orphanSprites.crop(48, 16, width/2, height/2);


        orphanIdleLeft = orphan_left[3];
        orphanIdleRight = orphan_right[0];

        // ENVIRONMENT
//        roof = mapsheet.crop(width * 9, 16, width, height - 2);
        computer = mapsheet.crop(0, 0, width, height);
        bed = mapsheet.crop(width, 0, width, height + 16);
//        door = mapsheet.crop(237, 16, 21, 31);

        // Tileset Spritesheet
        // Crops the texture from the spritesheet. Tiles are always 64x64 pixels in this sprite sheet
        // So we can use an row/column * 64 to quickly obtain the starting position of the sprite.
        pinkFloor = tileSpritesheet.crop((4 -1)* tileWidth,(1 - 1) * tileHeight, tileWidth, tileHeight);
        brickWall = tileSpritesheet.crop((2 - 1)* tileWidth,(1 - 1)* tileHeight, tileWidth, tileHeight);
        doorwayWall = tileSpritesheet.crop((3 - 1)* tileWidth,(1 - 1) * tileHeight, tileWidth, tileHeight);
        damagedFloor = tileSpritesheet.crop((1 - 1) * tileWidth,(2 - 1)* tileHeight, tileWidth, tileHeight);

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

        // Items
        sword = itemSprites.crop(16, 0, 16, 16);

        // Barrels
        barrel1 = barrelSprites.crop(0, 0, 32, 32);
        barrel2 = barrelSprites.crop(0, 32, 32, 32);
    }
}
