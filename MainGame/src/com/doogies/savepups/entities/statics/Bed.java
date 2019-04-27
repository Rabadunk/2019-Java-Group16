package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.assets.FurnitureAssets;
import com.doogies.savepups.items.Item;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bed extends Entity {

    private BufferedImage bedTexture = FurnitureAssets.bed;

    public Bed(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);

        bounds.x = 0;
        bounds.y = (int) (height - height/ 1.5f);
        bounds.width = width + 5;
        bounds.height = (int) (height - height / 2.0f);

        setHealth(1);
    }

    @Override
    public void tick() {
    }

    @Override
    public void die() {
        handler.getRoom().getItemManager().addItem(Item.coinGold.createNew((int) x, (int) y));
        handler.getRoom().getItemManager().addItem(Item.bedItem.createNew((int) x, (int) y + Tile.TILEHEIGHT));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(bedTexture,
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
