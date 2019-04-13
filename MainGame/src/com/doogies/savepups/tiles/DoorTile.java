package com.doogies.savepups.tiles;

import com.doogies.savepups.graphics.Assets;

public class DoorTile extends Tile {

    public DoorTile(int id) {
        super(Assets.door, id);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}
