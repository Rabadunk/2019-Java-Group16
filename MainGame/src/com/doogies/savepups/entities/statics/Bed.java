package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;

public class Bed extends StaticEntity {

    public Bed(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bed,
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                width, height, null);
    }
}
