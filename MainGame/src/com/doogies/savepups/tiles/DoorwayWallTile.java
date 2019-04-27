package com.doogies.savepups.tiles;

import com.doogies.savepups.graphics.assets.TileAssets;

public class DoorwayWallTile extends Tile {


    public DoorwayWallTile(int id) {
        super(TileAssets.doorwayWall, id, false);
    }

    @Override
    public boolean isEntry() { return true; }

}
