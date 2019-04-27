package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.graphics.assets.FurnitureAssets;
import com.doogies.savepups.items.Item;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShelfWithPot extends Entity {

    private BufferedImage shelfWithPot = FurnitureAssets.shelfWithPot;

    public ShelfWithPot(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH/2, Tile.TILEHEIGHT);

        bounds.x = 0;
        bounds.y = Tile.TILEHEIGHT/2;
        bounds.width = width;
        bounds.height = height/4;

        setHealth(2);
    }

    @Override
    public void tick() {
    }

    @Override
    public void die() {
        handler.getRoom().getItemManager().addItem(Item.coinGold.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(shelfWithPot,
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                width, height, null);

        // Red rectangle to represent players collision box
        g.setColor(Color.red);
        g.drawRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);
    }
}
