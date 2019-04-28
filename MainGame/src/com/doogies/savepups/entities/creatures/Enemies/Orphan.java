package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.creatures.Creature;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.items.Item;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Orphan extends Enemy {

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private boolean attackUp, attackDown, attackLeft, attackRight;

    // Attack timer
    private long attackCooldown = 3000;

    public Orphan(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH / 2, Creature.DEFAULT_CREATURE_HEIGHT / 2);

        attackUp = attackDown = attackLeft = attackRight = false;

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 32;
        bounds.height = 32;

        setupAttack();

        loadSprites();
        setSpeed(1f);
        setHealth(1);
    }

    private void loadSprites() {
        //Animations
        animationDown = new Animation(64, Assets.orphan_left);
        animationUp = new Animation(64, Assets.orphan_right);
        animationLeft = new Animation(64, Assets.orphan_left);
        animationRight = new Animation(64, Assets.orphan_right);
    }


    @Override
    public void tick() {
        //Animations
        animationDown.tick();
        animationUp.tick();
        animationLeft.tick();
        animationRight.tick();

        //Movement
        basicEnemyMoveTick();
    }

    @Override
    public void die(){
        handler.getRoom().getItemManager().addItem(Item.coinGold.createNew((int) x, (int) y));
        handler.getRoom().getItemManager().addItem(Item.life.createNew((int) x + 20, (int) y));
        goldCoinDrop.play();
    }

    @Override
    public void render(Graphics g) {

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
        else{
            // 0 = down, 1 = up, 2 = left, 3 = right
            if(direction == 0) {
                return Assets.orphanIdleLeft;
            }
            else if(direction == 1) {
                return Assets.orphanIdleRight;
            }
            else if(direction == 2) {
                return Assets.orphanIdleRight;
            }
            else if(direction == 3) {
                return Assets.orphanIdleLeft;
            }
        }
        return Assets.enemyIdleDown;
    }

}
