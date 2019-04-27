package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.graphics.assets.FurnitureAssets;
import com.doogies.savepups.items.Item;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Computer extends Entity {

    private BufferedImage computerTexture = FurnitureAssets.computer;

    public Computer(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT);

        bounds.x = 10;
        bounds.y = height/2 + 10;
        bounds.width = width - 24;
        bounds.height = height/8;

        setHealth(3);
    }

    @Override
    public void tick() {
    }

    @Override
    public void die() {
        handler.getRoom().getItemManager().addItem(Item.coinGold.createNew((int) x, (int) y));
        handler.getRoom().getItemManager().addItem(Item.coinGold.createNew((int) x + Tile.TILEWIDTH, (int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(computerTexture,
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
