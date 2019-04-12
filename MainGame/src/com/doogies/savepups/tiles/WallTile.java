package com.doogies.savepups.tiles;

import com.doogies.savepups.graphics.Assets;

import java.awt.image.BufferedImage;

public class WallTile extends Tile {

    public WallTile(int id) {
        super(Assets.wall, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
