package com.doogies.savepups.tiles;

import com.doogies.savepups.graphics.Assets;

public class DoorwayWallTile extends Tile {

    public DoorwayWallTile(int id) {
        super(Assets.doorwayWall, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
