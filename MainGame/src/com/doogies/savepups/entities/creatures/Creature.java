package com.doogies.savepups.entities.creatures;

import com.doogies.savepups.Game;
import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.tiles.Tile;
import com.doogies.savepups.world.World;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 1.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        if(!checkEntityCollision(xMove,0f)) {
            moveX();
        }
        if(!checkEntityCollision(0f,yMove)) {
            moveY();
        }

        if(inEntry((int) ((x + bounds.x + bounds.width) / Tile.TILEHEIGHT), (int) ((y + bounds.y + bounds.height) / Tile.TILEWIDTH))) {
            System.out.println("You're on an entry tile!");
            System.out.println((int)((x + bounds.x + bounds.width) / Tile.TILEHEIGHT) + " " + (int) ((y + bounds.y + bounds.height) / Tile.TILEWIDTH));
            System.out.println(getCurrentTileID());
        }

        System.out.println(getCurrentTileID());
    }

    public void moveX() {
        if(xMove > 0) {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                                        !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }

        } else if (xMove < 0) {
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if(yMove < 0) { // up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEWIDTH;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }

        } else if (yMove > 0) { // down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEWIDTH;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    protected boolean inEntry(int x, int y) { return handler.getWorld().getTile(x, y).isEntry(); }

    protected int getCurrentTileID() {
        return handler.getWorld().getTile((int)((x + bounds.x + bounds.width) / Tile.TILEHEIGHT), (int) ((y + bounds.y + bounds.height) / Tile.TILEWIDTH)).getWorldId();
    }



    //GETTERS SETTERS

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
