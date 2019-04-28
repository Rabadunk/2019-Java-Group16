package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.graphics.assets.FurnitureAssets;
import com.doogies.savepups.items.Item;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tv extends Entity {

    private BufferedImage tv = FurnitureAssets.tv;

    public Tv (Handler handler, float x, float y){
        super(handler, x, y, Tile.TILEWIDTH *3, Tile.TILEHEIGHT*2);

        bounds.x = 0;
        bounds.y = Tile.TILEHEIGHT;
        bounds.width = width - 12;
        bounds.height = height/2;

        setHealth(4);
    }

    @Override
    public void tick() {
    }

    @Override
    public void die() {
        handler.getRoom().getItemManager().addItem(Item.coinGold.createNew((int) x, (int) y + Tile.TILEHEIGHT));
        handler.getRoom().getItemManager().addItem(Item.coinGold.createNew((int) x + Tile.TILEWIDTH, (int) y + Tile.TILEHEIGHT));
        handler.getRoom().getItemManager().addItem(Item.coinGold.createNew((int) x + Tile.TILEWIDTH*2, (int) y + Tile.TILEHEIGHT));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tv,
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
