package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.creatures.Creature;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.house.AStarNode;
import com.doogies.savepups.tiles.Tile;

import java.util.Random;

public abstract class Enemy extends Creature {

    int diameter;
    float dx, dy;
    int count;

    Player player;
    protected int direction = 0;
    protected AStarNode moveTo;

    public Enemy(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        player = handler.getPlayer();
        diameter = 200;
        moveTo = new AStarNode((int) x, (int) y, Tile.tiles[0].getTexture(), false, handler);
    }

    protected void moveToPlayer() {

        AStarNode goalNode = handler.getRoom().getPathFinder().getNode(
                (int) ((handler.getPlayer().getX() + handler.getPlayer().getBounds().x) / Tile.TILEWIDTH),
                (int) ((handler.getPlayer().getY() + handler.getPlayer().getBounds().y) / Tile.TILEHEIGHT)
        );

        AStarNode startNode = handler.getRoom().getPathFinder().getNode(
                (int) ((x + bounds.x) / Tile.TILEWIDTH),
                (int) ((y + bounds.y) / Tile.TILEHEIGHT)
        );

        System.out.println("PlayerNodeX: " + goalNode.x + " PlayerNodeY: " + goalNode.y);
        System.out.println("Our Location: " + startNode.x + " " + startNode.y);

        AStarNode node = handler.getRoom().getPathFinder().pathFind(startNode, goalNode);

        if(y > (node.y * Tile.TILEHEIGHT) + 1) {
            yMove = -speed;
            direction = 1;
        }

        if(y < (node.y * Tile.TILEHEIGHT) - 1) {
            yMove = speed;
            direction = 0;
        }

        if(x > (node.x * Tile.TILEWIDTH) + 1) {
            xMove = -speed;
            direction = 2;
        }

        if(x < (node.x * Tile.TILEWIDTH) - 1) {
            xMove = speed;
            direction = 3;
        }

        System.out.println("Actually moving to: " + node.x + " " + node.y);
        System.out.println();

        this.dx = (x + width/2 - handler.getGameCamera().getxOffset()) - (player.getX() - handler.getGameCamera().getxOffset() + player.getWidth() / 2);
        this.dy = (y + height/2 - handler.getGameCamera().getyOffset()) - (player.getY() - handler.getGameCamera().getyOffset() + player.getHeight() / 2);


        if((Math.sqrt(this.dx*this.dx + this.dy * this.dy)) < 0.8 * Tile.TILEWIDTH) {
            dontMove();
        }

    }

    protected void autoMoveDecider() {
        count = 0;

        if(new Random().nextInt(5) == 0){
            xMove = speed;
        }
        if(new Random().nextInt(5) == 1){
            xMove = -speed;
        }
        if(new Random().nextInt(5) == 2){
            yMove = speed;
        }
        if(new Random().nextInt(5) == 3){
            yMove = -speed;
        }
        if(new Random().nextInt(5) == 4) {
            dontMove();
        }

    }

    protected void dontMove() {
        yMove = 0;
        xMove = 0;
    }

    @Override
    protected boolean collisionWithTile(int x, int y) {
        return handler.getRoom().getPathFinder().getNode(x, y).isEntry() || handler.getRoom().getPathFinder().getNode(x, y).isSolid;
    }

    protected boolean colCircleBox(Player player) {

        float dx = (x + width/2 - handler.getGameCamera().getxOffset()) - (player.getX() - handler.getGameCamera().getxOffset() + player.getWidth() / 4);

        float dy = (y + height/2 - handler.getGameCamera().getyOffset()) - (player.getY() + player.getBounds().y - handler.getGameCamera().getyOffset() + player.getHeight() / 4);

        if(Math.sqrt(dx * dx + dy * dy) < (diameter /2 + player.getWidth() /2)) {
            return true;
        }

        return false;
    }
}
