package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.graphics.assets.FurnitureAssets;
import com.doogies.savepups.items.Item;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Radio extends Entity {

    private BufferedImage radio = FurnitureAssets.radio;

    public Radio(Handler handler, float x, float y) {
        super(handler, x + Tile.TILEWIDTH/4, y, Tile.TILEWIDTH/2, Tile.TILEHEIGHT/2);

        bounds.x = 0;
        bounds.y = Tile.TILEHEIGHT/4;
        bounds.width = width;
        bounds.height = 3;

        setHealth(1);
    }

    @Override
    public void tick() {
    }

    @Override
    public void die() {
        handler.getRoom().getItemManager().addItem(Item.coinGold.createNew((int) x, (int) y));
        goldCoinDrop.play();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(radio,
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                width, height, null);
    }

}
