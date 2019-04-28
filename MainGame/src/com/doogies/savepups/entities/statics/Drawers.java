package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioManager;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.graphics.assets.FurnitureAssets;
import com.doogies.savepups.items.Item;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Drawers extends Entity {

    private BufferedImage drawersTexture = FurnitureAssets.drawers;

    public Drawers(Handler handler, float x, float y) {
        super(handler, x + Tile.TILEWIDTH/4, y, Tile.TILEWIDTH/2, Tile.TILEHEIGHT);

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
        AudioManager.goldCoinDrop.play();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(drawersTexture,
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                width, height, null);

    }
}
