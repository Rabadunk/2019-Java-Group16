package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.entities.creatures.Creature;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.house.AStarNode;
import com.doogies.savepups.tiles.Tile;
import com.doogies.savepups.utils.GameTimer;

import java.awt.*;
import java.util.Random;

public abstract class Enemy extends Creature {

    int diameter;
    float dx, dy;
    int count;

    Player player;
    protected int direction = 0;
    protected AStarNode moveTo;

    protected Rectangle attackRectangle;
    int attackRangeSize = bounds.width;

    // Attack timer
    protected long lastAttackTimer, attackCooldown = 1800, attackTimer = attackCooldown;

    // Game Timer
    protected GameTimer gameTimer;
    protected boolean timerStart = false;
    protected boolean timerSet = false;
    protected int timeTakenMinutes, timeTakenSeconds = 0;
    protected long initalTime;
    protected boolean playerActive = false;


    protected boolean attackUp, attackDown, attackLeft, attackRight;

    public Enemy(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        player = handler.getPlayer();
        diameter = 200;
        moveTo = new AStarNode((int) x, (int) y, Tile.tiles[0].getTexture(), false, handler);
    }

    protected void setupAttack() {
        attackUp = attackDown = attackLeft = attackRight = false;
        attackRectangle = new Rectangle();
        attackRectangle.width = bounds.width;
        attackRectangle.height = bounds.height;
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

        //System.out.println("PlayerNodeX: " + goalNode.x + " PlayerNodeY: " + goalNode.y);
        //System.out.println("Our Location: " + startNode.x + " " + startNode.y);

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


        //System.out.println("Actually moving to: " + node.x + " " + node.y);
       // System.out.println();

        this.dx = (x + width/2 - handler.getGameCamera().getxOffset()) - (player.getX() - handler.getGameCamera().getxOffset() + player.getWidth() / 2);
        this.dy = (y + height/2 - handler.getGameCamera().getyOffset()) - (player.getY() - handler.getGameCamera().getyOffset() + player.getHeight() / 2);

        if(getDistanceToPlayer() < 0.8 * Tile.TILEWIDTH) {
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

    protected void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if (attackTimer < attackCooldown) {
            return;
        }

        Rectangle enemyBounds = getCollisionBounds(0, 0);
        Boolean shouldAttack = getDistanceToPlayer() < 0.8 * Tile.TILEWIDTH;

        System.out.println(shouldAttack);

        attackUp = attackDown = attackLeft = attackRight = false;

        // 0 = down, 1 = up, 2 = left, 3 = right

        // Up
        if (shouldAttack && direction == 1) {
            attackRectangle.x = enemyBounds.x;
            attackRectangle.y = enemyBounds.y - enemyBounds.height;
            attackUp = true;
            playerActive = true;
        }
        // Down
        else if (shouldAttack && direction == 0) {
            attackRectangle.x = enemyBounds.x;
            attackRectangle.y = enemyBounds.y + enemyBounds.height;
            attackDown = true;
            playerActive = true;
        }
        // Left
        else if (shouldAttack && direction == 2) {
            attackRectangle.x = enemyBounds.x - enemyBounds.width;
            attackRectangle.y = enemyBounds.y;
            attackLeft = true;
            playerActive = true;
        }
        // Right
        else if (shouldAttack && direction == 3) {
            attackRectangle.x = enemyBounds.x + enemyBounds.width;
            attackRectangle.y = enemyBounds.y;
            attackRight = true;
            playerActive = true;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getRoom().getEntityManager().getEntities()) {
            if (e.equals(handler.getPlayer()) && e.getCollisionBounds(0,0).intersects(attackRectangle)) {
                e.damage(1);
            }
        }
    }

    protected void timeTracker(){

        if(playerActive && !timerSet){
            initalTime = System.currentTimeMillis();
            timerSet = true;
        }

        if(!playerActive) {
            timeTakenMinutes = 0;
            timeTakenSeconds = 0;
        }
        else {
            timeTakenMinutes = (int) (System.currentTimeMillis() - initalTime) / 1000 / 60;
            timeTakenSeconds = (int) ((System.currentTimeMillis() - initalTime) / 1000) % 60;
        }
    }

    protected float getDistanceToPlayer() {
        this.dx = (x + width/2 - handler.getGameCamera().getxOffset()) - (player.getX() - handler.getGameCamera().getxOffset() + player.getWidth() / 2);
        this.dy = (y + height/2 - handler.getGameCamera().getyOffset()) - (player.getY() - handler.getGameCamera().getyOffset() + player.getHeight() / 2);

        return (float) Math.sqrt(this.dx*this.dx + this.dy * this.dy);
    }
}
