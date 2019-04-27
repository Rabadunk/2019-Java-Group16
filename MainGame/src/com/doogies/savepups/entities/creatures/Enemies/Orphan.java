package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.entities.creatures.Creature;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.assets.FurnitureAssets;
import com.doogies.savepups.items.Item;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Orphan extends Enemy {

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private boolean attackUp, attackDown, attackLeft, attackRight;

    // Attack timer
    private long attackCooldown = 3000;

    // Player Direction
    // 0 = down, 1 = up, 2 = left, 3 = right


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
        if(colCircleBox(handler.getPlayer())&& !(player.getCurrentAnimationFrame() == FurnitureAssets.bed)) {
            diameter = 600;
            moveToPlayer();
            move();
            System.out.println("moving to player");
            checkAttacks();
        } else {
            count ++;
            if(count > 30) {
                autoMoveDecider();
            }
            move();

            diameter = 200;
        }
        timeTracker();
    }

    @Override
    public void die(){
        System.out.println("Ogre has been slain");
        handler.getRoom().getItemManager().addItem(Item.coinGold.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentAnimationFrame(),
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                width, height, null);

        //DOesnt work
        // Red rectangle to represent players collision box
//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
        // Oval around enemy

        g.setColor(Color.blue);
        g.drawOval((int)(x + width/2 - handler.getGameCamera().getxOffset() - diameter / 2),
                (int)(y + height/2 - handler.getGameCamera().getyOffset() - diameter / 2), diameter, diameter);

        g.setColor(Color.red);
        g.drawRect((int) (attackRectangle.x - handler.getGameCamera().getxOffset()),
                (int) (attackRectangle.y - handler.getGameCamera().getyOffset()),
                attackRectangle.width,
                attackRectangle.height);

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
