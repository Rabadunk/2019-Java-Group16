package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioManager;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.assets.FurnitureAssets;
import com.doogies.savepups.items.Item;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PotPlant extends Entity {

    private BufferedImage potPlantTexture = FurnitureAssets.potPlant;

    public PotPlant(Handler handler, float x, float y) {
        super(handler, x + Tile.TILEWIDTH/4, y + Tile.TILEHEIGHT/4, Tile.TILEWIDTH/2, Tile.TILEHEIGHT/2);

        bounds.x = 0;
        bounds.y = height/2;
        bounds.width = width;
        bounds.height = height/4;

        setHealth(1);
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
        g.drawImage(potPlantTexture,
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                width, height, null);
    }


}