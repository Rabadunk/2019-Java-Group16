package com.doogies.savepups.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.doogies.savepups.graphics.assets.EnemyAssets;
import com.doogies.savepups.graphics.assets.FurnitureAssets;
import com.doogies.savepups.graphics.assets.PlayerAssets;
import com.doogies.savepups.graphics.assets.TileAssets;
import com.doogies.savepups.tiles.Tile;

public class Assets {

    private static final int width = 32, height = 32;

    public static Font font28, fontTitle, fontHud, fontTitleBig, fontChen, fontChenSmaller;

    //public static BufferedImage computer, bed, wall, roof, floor, damagedFloor, door;

    public static BufferedImage doogie;

    public static BufferedImage barrel1, barrel2;

    public static BufferedImage[] enemy_down, enemy_up, enemy_left, enemy_right;
    public static BufferedImage enemyIdleDown, enemyIdleUp, enemyIdleLeft, enemyIdleRight;

    public static BufferedImage attack;

    public static BufferedImage sword;

    public static BufferedImage[] life;

    // Coins
    public static BufferedImage[] coinGold, coinSilver, coinCopper;

    // ui
    public static BufferedImage[] playButton, scoreButton, quitButton, blankButton;
    public static BufferedImage inventoryScreen;
    public static BufferedImage tint;

    // variables to make tileset calcs cleaner
    private static final int tileHeight = Tile.TILEHEIGHT;
    private static final int tileWidth = Tile.TILEHEIGHT;

    //
    public static BufferedImage dog;

    public static void init() {

        TileAssets.init();
        FurnitureAssets.init();
        PlayerAssets.init();
        EnemyAssets.init();

        font28 = FontLoader.loadFont("res/fonts/slkscr/slkscr.ttf", 28);
        fontTitle = FontLoader.loadFont("res/fonts/Pacifico/Pacifico.ttf", 76);
        fontTitleBig = FontLoader.loadFont("res/fonts/Pacifico/Pacifico.ttf", 96);
        fontHud = FontLoader.loadFont("res/fonts/ArmWrestler/ArmWrestler.ttf", 28);
        fontChen = FontLoader.loadFont("res/fonts/akaChen/akaChen.ttf", 46);
        fontChenSmaller = FontLoader.loadFont("res/fonts/akaChen/akaChen.ttf", 32);

        SpriteSheet blankbutton = new SpriteSheet(ImageLoader.loadImage("/ui/blankButtons.png"));

        SpriteSheet itemSprites = new SpriteSheet(ImageLoader.loadImage("/textures/items.png"));

        SpriteSheet barrelSprites = new SpriteSheet(ImageLoader.loadImage("/textures/barrels/barrels.png"));
        SpriteSheet coinGoldSprites = new SpriteSheet(ImageLoader.loadImage("/textures/coins/coin_gold.png"));
        SpriteSheet coinSilverSprites = new SpriteSheet(ImageLoader.loadImage("/textures/coins/coin_silver.png"));
        SpriteSheet coinCopperSprites = new SpriteSheet(ImageLoader.loadImage("/textures/coins/coin_copper.png"));
        SpriteSheet objects = new SpriteSheet(ImageLoader.loadImage("/textures/gfx/objects.png"));

        //Dooogies
        SpriteSheet dogSheet = new SpriteSheet(ImageLoader.loadImage("/textures/doogies/lpccatratdog.png"));

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
        tint = ImageLoader.loadImage("/ui/tint.png");


        doogie = ImageLoader.loadImage("/textures/doogies/doogie.png");

        // UI

        blankButton = new BufferedImage[2];
        blankButton[0] = blankbutton.crop(0, 300, 300, 150); // Not selected
        blankButton[1] = blankbutton.crop(300, 300, 300, 150);

        // Items
        sword = itemSprites.crop(16, 0, 16, 16);

        life = new BufferedImage[4];
        life[0] = objects.crop(0,48, 16, 16);
        life[1] = objects.crop(16,48, 16, 16);
        life[2] = objects.crop(32,48, 16, 16);
        life[3] = objects.crop(48,48, 16, 16);

        dog = dogSheet.crop(128, 160, 48,48);


        // Barrels
        barrel1 = barrelSprites.crop(0, 0, 32, 32);
        barrel2 = barrelSprites.crop(0, 32, 32, 32);

        // Coins
        coinGold = new BufferedImage[8];
        coinGold[0] = coinGoldSprites.crop(0,0, 32, 32);
        coinGold[1] = coinGoldSprites.crop(32,0, 32, 32);
        coinGold[2] = coinGoldSprites.crop(64,0, 32, 32);
        coinGold[3] = coinGoldSprites.crop(96,0, 32, 32);
        coinGold[4] = coinGoldSprites.crop(128,0, 32, 32);
        coinGold[5] = coinGoldSprites.crop(160,0, 32, 32);
        coinGold[6] = coinGoldSprites.crop(192,0, 32, 32);
        coinGold[7] = coinGoldSprites.crop(224,0, 32, 32);

        coinSilver = new BufferedImage[8];
        coinSilver[0] = coinSilverSprites.crop(0,0, 32, 32);
        coinSilver[1] = coinSilverSprites.crop(32,0, 32, 32);
        coinSilver[2] = coinSilverSprites.crop(64,0, 32, 32);
        coinSilver[3] = coinSilverSprites.crop(96,0, 32, 32);
        coinSilver[4] = coinSilverSprites.crop(128,0, 32, 32);
        coinSilver[5] = coinSilverSprites.crop(160,0, 32, 32);
        coinSilver[6] = coinSilverSprites.crop(192,0, 32, 32);
        coinSilver[7] = coinSilverSprites.crop(224,0, 32, 32);

        coinCopper = new BufferedImage[8];
        coinCopper[0] = coinCopperSprites.crop(0,0, 32, 32);
        coinCopper[1] = coinCopperSprites.crop(32,0, 32, 32);
        coinCopper[2] = coinCopperSprites.crop(64,0, 32, 32);
        coinCopper[3] = coinCopperSprites.crop(96,0, 32, 32);
        coinCopper[4] = coinCopperSprites.crop(128,0, 32, 32);
        coinCopper[5] = coinCopperSprites.crop(160,0, 32, 32);
        coinCopper[6] = coinCopperSprites.crop(192,0, 32, 32);
        coinCopper[7] = coinCopperSprites.crop(224,0, 32, 32);

    }
}
