package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioManager;
import com.doogies.savepups.entities.creatures.Creature;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.assets.EnemyAssets;
import com.doogies.savepups.house.AStarNode;
import com.doogies.savepups.input.KeyManager;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Witch extends  Enemy{
    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;

    private boolean shouldMove = false, shouldCenter = false;

    // Player Direction
    // 0 = down, 1 = up, 2 = left, 3 = right

    public Witch(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH/2, Creature.DEFAULT_CREATURE_HEIGHT);

        attackUp = attackDown = attackLeft = attackRight = false;

        bounds.x = 0;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;
        setupAttack();
        loadSprites();

        setSpeed(5);
        setHealth(2);
    }

    private void loadSprites() {
        //Animations
        animationDown = new Animation(64, EnemyAssets.witch_left);
        animationUp = new Animation(64, EnemyAssets.witch_right);
        animationLeft = new Animation(64, EnemyAssets.witch_left);
        animationRight = new Animation(64, EnemyAssets.witch_right);
    }

    @Override
    public void tick() {
        //Animations
        animationDown.tick();
        animationUp.tick();
        animationLeft.tick();
        animationRight.tick();

        glitchCollisionRespawn();

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
            shouldMove = !shouldMove;
        }

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) {
            player.centerOnPlayer = !player.centerOnPlayer;
            shouldCenter = !shouldCenter;
        }

        if(shouldMove) {
            //Movement
            moveToPlayer(Tile.TILEHEIGHT);
            move();
        }

        if(shouldCenter) {
            handler.getGameCamera().centerOnEntity(this);
        } else {
            handler.getGameCamera().centerOnEntity(player);
        }
    }

    @Override
    public void die(){
        basicEnemyDeath();
    }

    @Override
    public void render(Graphics g) {

        if(shouldMove) {
            AStarNode goalNode = handler.getRoom().getPathFinder().getNode(
                    (int) ((handler.getPlayer().getX() + handler.getPlayer().getBounds().x) / Tile.TILEWIDTH),
                    (int) ((handler.getPlayer().getY() + handler.getPlayer().getBounds().y) / Tile.TILEHEIGHT)
            );

            AStarNode startNode = handler.getRoom().getPathFinder().getNode(
                    (int) ((x + bounds.x) / Tile.TILEWIDTH),
                    (int) ((y + bounds.y) / Tile.TILEHEIGHT)
            );

            handler.getRoom().getPathFinder().renderPathFind(g, startNode, goalNode);
        }

        g.drawImage(getCurrentAnimationFrame(),
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                width, height, null);
    }

    // Getters and setters

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove <0){
            return animationLeft.getCurrentFrame();
        }
        else if(xMove > 0){
            return animationRight.getCurrentFrame();
        }
        else if(yMove < 0) {
            return animationUp.getCurrentFrame();
        }
        else if(yMove > 0){
            return animationDown.getCurrentFrame();
        }

        return EnemyAssets.witchIdleLeft;
    }
}
