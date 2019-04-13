package com.doogies.savepups.tiles;

import com.doogies.savepups.graphics.Assets;

import java.awt.image.BufferedImage;

public class BrickWallTile extends Tile {

    public BrickWallTile(int id) {
        super(Assets.brickWall, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
