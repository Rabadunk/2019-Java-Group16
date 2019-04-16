package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;

public class Bed extends StaticEntity {

    public Bed(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);

        bounds.x = 0;
        bounds.y = (int) (height - height/ 1.5f);
        bounds.width = width + 5;
        bounds.height = (int) (height - height / 2.0f);
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

        // Red rectangle to represent players collision box
//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//               bounds.width, bounds.height);
    }
}