package com.doogies.savepups.tiles;

import com.doogies.savepups.graphics.assets.TileAssets;

public class BrickWallTile extends Tile {

    public BrickWallTile(int id) {
        super(TileAssets.brickWall, id, true);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

}
