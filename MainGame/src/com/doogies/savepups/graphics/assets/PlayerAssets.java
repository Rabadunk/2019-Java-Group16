package com.doogies.savepups.graphics.assets;

import com.doogies.savepups.graphics.ImageLoader;
import com.doogies.savepups.graphics.SpriteSheet;
import com.doogies.savepups.tiles.Tile;

import java.awt.image.BufferedImage;

public class PlayerAssets {

    private static SpriteSheet playersheet, playerAttackedsheet;
    private static final int width = Tile.TILEWIDTH/2, height = Tile.TILEHEIGHT/2;

    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage  playerIdleUp, playerIdleDown, playerIdleLeft, playerIdleRight;

    public static BufferedImage[] playerAttackDown, playerAttackUp, playerAttackLeft, playerAttackRight;

    public static BufferedImage[] playerAttacked_down, playerAttacked_up, playerAttacked_left, playerAttacked_right;
    public static BufferedImage  playerAttackedIdleUp, playerAttackedIdleDown, playerAttackedIdleLeft, playerAttackedIdleRight;

    public static BufferedImage[] playerAttackedAttackDown, playerAttackedAttackUp, playerAttackedAttackLeft, playerAttackedAttackRight;


    public static void init() {
        playersheet = new SpriteSheet(ImageLoader.loadImage("/textures/gfx/character.png"));
        playerAttackedsheet = new SpriteSheet(ImageLoader.loadImage("/textures/gfx/characterAttacked.png"));


        // Player
        player_up = new BufferedImage[4];
        player_up[0] = playersheet.crop(0,64, width/2, height);
        player_up[1] = playersheet.crop(width/2,64, width/2, height);
        player_up[2] = playersheet.crop(width,64, width/2, height);
        player_up[3] = playersheet.crop(width + width/2,64, width/2, height);

        player_down = new BufferedImage[4];
        player_down[0] = playersheet.crop(0,0,width/2, height);
        player_down[1] = playersheet.crop(width/2,0, width/2, height);
        player_down[2] = playersheet.crop(width,0, width/2, height);
        player_down[3] = playersheet.crop(3*(width/2),0, width/2, height);

        player_left = new BufferedImage[4];
        player_left[0] = playersheet.crop(0,3*height, width/2, height);
        player_left[1] = playersheet.crop(width/2,3*height, width/2, height);
        player_left[2] = playersheet.crop(width,3*height, width/2, height);
        player_left[3] = playersheet.crop(3*(width/2),3*height, width/2, height);

        player_right = new BufferedImage[4];
        player_right[0] = playersheet.crop(0, height, width/2, height);
        player_right[1] = playersheet.crop(width/2, height, width/2, height);
        player_right[2] = playersheet.crop(width, height, width/2, height);
        player_right[3] = playersheet.crop(3*(width/2), height, width/2, height);


        playerIdleUp = player_up[0];
        playerIdleDown = player_down[0];
        playerIdleLeft = player_left[0];
        playerIdleRight = player_right[0];

        playerAttackUp = new BufferedImage[4];
        playerAttackUp[0] = playersheet.crop(0 + 8,16 * 10, width/2, height);
        playerAttackUp[1] = playersheet.crop( 32 + 8,16 * 10, width/2, height);
        playerAttackUp[2] = playersheet.crop(2* 32 + 8,16 * 10, width/2, height);
        playerAttackUp[3] = playersheet.crop(3* 32 + 8,16 * 10, width/2, height);

        playerAttackDown = new BufferedImage[4];
        playerAttackDown[0] = playersheet.crop(0 + 8,16 * 8, width/2, height);
        playerAttackDown[1] = playersheet.crop( 32 + 8,16 * 8, width/2, height);
        playerAttackDown[2] = playersheet.crop(2* 32 + 8,16 * 8, width/2, height);
        playerAttackDown[3] = playersheet.crop(3* 32 + 8,16 * 8, width/2, height);

        playerAttackLeft = new BufferedImage[4];
        playerAttackLeft[0] = playersheet.crop(0 + 8, 16 * 14, width/2, height);
        playerAttackLeft[1] = playersheet.crop( 32 + 8, 16 * 14, width/2, height);
        playerAttackLeft[2] = playersheet.crop(2* 32 + 8, 16 * 14, width/2, height);
        playerAttackLeft[3] = playersheet.crop(3* 32 + 8, 16 * 14, width/2, height);

        playerAttackRight = new BufferedImage[4];
        playerAttackRight[0] = playersheet.crop(0 + 8, 16 * 12, width/2, height);
        playerAttackRight[1] = playersheet.crop( 32 + 8, 16 * 12, width/2, height);
        playerAttackRight[2] = playersheet.crop(2* 32 + 8, 16 * 12, width/2, height);
        playerAttackRight[3] = playersheet.crop(3* 32 + 8, 16 * 12, width/2, height);

        // Player taken damage

        playerAttacked_up = new BufferedImage[4];
        playerAttacked_up[0] = playerAttackedsheet.crop(0,64, width/2, height);
        playerAttacked_up[1] = playerAttackedsheet.crop(width/2,64, width/2, height);
        playerAttacked_up[2] = playerAttackedsheet.crop(width,64, width/2, height);
        playerAttacked_up[3] = playerAttackedsheet.crop(width + width/2,64, width/2, height);

        playerAttacked_down = new BufferedImage[4];
        playerAttacked_down[0] = playerAttackedsheet.crop(0,0,width/2, height);
        playerAttacked_down[1] = playerAttackedsheet.crop(width/2,0, width/2, height);
        playerAttacked_down[2] = playerAttackedsheet.crop(width,0, width/2, height);
        playerAttacked_down[3] = playerAttackedsheet.crop(3*(width/2),0, width/2, height);

        playerAttacked_left = new BufferedImage[4];
        playerAttacked_left[0] = playerAttackedsheet.crop(0,3*height, width/2, height);
        playerAttacked_left[1] = playerAttackedsheet.crop(width/2,3*height, width/2, height);
        playerAttacked_left[2] = playerAttackedsheet.crop(width,3*height, width/2, height);
        playerAttacked_left[3] = playerAttackedsheet.crop(3*(width/2),3*height, width/2, height);

        playerAttacked_right = new BufferedImage[4];
        playerAttacked_right[0] = playerAttackedsheet.crop(0, height, width/2, height);
        playerAttacked_right[1] = playerAttackedsheet.crop(width/2, height, width/2, height);
        playerAttacked_right[2] = playerAttackedsheet.crop(width, height, width/2, height);
        playerAttacked_right[3] = playerAttackedsheet.crop(3*(width/2), height, width/2, height);


        playerAttackedIdleUp = playerAttacked_up[0];
        playerAttackedIdleDown = playerAttacked_down[0];
        playerAttackedIdleLeft = playerAttacked_left[0];
        playerAttackedIdleRight = playerAttacked_right[0];

        playerAttackedAttackUp = new BufferedImage[4];
        playerAttackedAttackUp[0] = playerAttackedsheet.crop(0 + 8,16 * 10, width/2, height);
        playerAttackedAttackUp[1] = playerAttackedsheet.crop( 32 + 8,16 * 10, width/2, height);
        playerAttackedAttackUp[2] = playerAttackedsheet.crop(2* 32 + 8,16 * 10, width/2, height);
        playerAttackedAttackUp[3] = playerAttackedsheet.crop(3* 32 + 8,16 * 10, width/2, height);

        playerAttackedAttackDown = new BufferedImage[4];
        playerAttackedAttackDown[0] = playerAttackedsheet.crop(0 + 8,16 * 8, width/2, height);
        playerAttackedAttackDown[1] = playerAttackedsheet.crop( 32 + 8,16 * 8, width/2, height);
        playerAttackedAttackDown[2] = playerAttackedsheet.crop(2* 32 + 8,16 * 8, width/2, height);
        playerAttackedAttackDown[3] = playerAttackedsheet.crop(3* 32 + 8,16 * 8, width/2, height);

        playerAttackedAttackLeft = new BufferedImage[4];
        playerAttackedAttackLeft[0] = playerAttackedsheet.crop(0 + 8, 16 * 14, width/2, height);
        playerAttackedAttackLeft[1] = playerAttackedsheet.crop( 32 + 8, 16 * 14, width/2, height);
        playerAttackedAttackLeft[2] = playerAttackedsheet.crop(2* 32 + 8, 16 * 14, width/2, height);
        playerAttackedAttackLeft[3] = playerAttackedsheet.crop(3* 32 + 8, 16 * 14, width/2, height);

        playerAttackedAttackRight = new BufferedImage[4];
        playerAttackedAttackRight[0] = playerAttackedsheet.crop(0 + 8, 16 * 12, width/2, height);
        playerAttackedAttackRight[1] = playerAttackedsheet.crop( 32 + 8, 16 * 12, width/2, height);
        playerAttackedAttackRight[2] = playerAttackedsheet.crop(2* 32 + 8, 16 * 12, width/2, height);
        playerAttackedAttackRight[3] = playerAttackedsheet.crop(3* 32 + 8, 16 * 12, width/2, height);

    }

}
