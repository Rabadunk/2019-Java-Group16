package com.doogies.savepups.graphics.assets;

import com.doogies.savepups.graphics.ImageLoader;
import com.doogies.savepups.graphics.SpriteSheet;

import java.awt.image.BufferedImage;

public class EnemyAssets {

    public static BufferedImage[] ogre_right, ogre_left;
    public static BufferedImage ogreIdleRight, ogreIdleLeft;

    public static BufferedImage[] orc_right, orc_left;
    public static BufferedImage orcIdleRight, orcIdleLeft;

    public static BufferedImage[] orphan_right, orphan_left;
    public static BufferedImage orphanIdleRight, orphanIdleLeft;

    public static BufferedImage[] screamer_right, screamer_left;
    public static BufferedImage screamerIdleRight, screamerIdleLeft;

    public static BufferedImage[] vampire_down, vampire_up, vampire_left, vampire_right;
    public static BufferedImage  vampireIdleUp, vampireIdleDown, vampireIdleLeft, vampireIdleRight;

    public static BufferedImage[] bat_down, bat_up, bat_left, bat_right;
    public static BufferedImage  batIdleUp, batIdleDown, batIdleLeft, batIdleRight;

    public static void init() {
        int width = 32, height = 32;

        SpriteSheet ogreSprites = new SpriteSheet(ImageLoader.loadImage("/textures/ogre.png"));
        SpriteSheet screamerSprites = new SpriteSheet(ImageLoader.loadImage("/textures/screamer.png"));
        SpriteSheet orcSprites = new SpriteSheet(ImageLoader.loadImage("/textures/orc.png"));
        SpriteSheet orphanSprites = new SpriteSheet(ImageLoader.loadImage("/textures/orphan.png"));
        SpriteSheet vampireSprites = new SpriteSheet(ImageLoader.loadImage("/textures/vampire.png"));

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

        // Vampire
        vampire_up = new BufferedImage[3];
        vampire_up[0] = vampireSprites.crop(0,height*3, width/2, height);
        vampire_up[1] = vampireSprites.crop(width/2,height*3, width/2, height);
        vampire_up[2] = vampireSprites.crop(width,height*3, width/2, height);

        vampire_down = new BufferedImage[3];
        vampire_down[0] = vampireSprites.crop(0,0, width/2, height);
        vampire_down[1] = vampireSprites.crop(width/2,0, width/2, height);
        vampire_down[2] = vampireSprites.crop(width,0, width/2, height);

        vampire_left = new BufferedImage[3];
        vampire_left[0] = vampireSprites.crop(0, height, width/2, height);
        vampire_left[1] = vampireSprites.crop(width/2, height, width/2, height);
        vampire_left[2] = vampireSprites.crop(width, height, width/2, height);

        vampire_right = new BufferedImage[3];
        vampire_right[0] = vampireSprites.crop(0, height*2, width/2, height);
        vampire_right[1] = vampireSprites.crop(width/2, height*2, width/2, height);
        vampire_right[2] = vampireSprites.crop(width, height*2, width/2, height);



        vampireIdleUp = vampire_up[1];
        vampireIdleDown = vampire_down[1];
        vampireIdleLeft = vampire_left[1];
        vampireIdleRight = vampire_right[1];

        // Bat
        bat_up = new BufferedImage[3];
        bat_up[0] = vampireSprites.crop(80,height*3, width, height);
        bat_up[1] = vampireSprites.crop(80 + width,height*3, width, height);
        bat_up[2] = vampireSprites.crop(80 + 2*width,height*3, width, height);

        bat_down = new BufferedImage[3];
        bat_down[0] = vampireSprites.crop(80,0, width, height);
        bat_down[1] = vampireSprites.crop(80 + width,0, width, height);
        bat_down[2] = vampireSprites.crop(80 + 2*width,0, width, height);

        bat_left = new BufferedImage[3];
        bat_left[0] = vampireSprites.crop(80, height, width, height);
        bat_left[1] = vampireSprites.crop(80 + width, height, width, height);
        bat_left[2] = vampireSprites.crop(80 + 2*width, height, width, height);

        bat_right = new BufferedImage[3];
        bat_right[0] = vampireSprites.crop(80, height*2, width, height);
        bat_right[1] = vampireSprites.crop(80 + width, height*2, width, height);
        bat_right[2] = vampireSprites.crop(80 + 2*width, height*2, width, height);


        batIdleUp = bat_up[1];
        batIdleDown = bat_down[1];
        batIdleLeft = bat_left[1];
        batIdleRight = bat_right[1];
    }


}
