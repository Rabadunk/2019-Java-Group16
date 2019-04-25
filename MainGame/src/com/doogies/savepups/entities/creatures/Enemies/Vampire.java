package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.creatures.Creature;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Vampire extends Enemy {

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private boolean attackUp, attackDown, attackLeft, attackRight;

    // Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    // Player Direction
    // 0 = down, 1 = up, 2 = left, 3 = right


    public Vampire(Handler handler, float x, float y) {
        super(handler, x, y, 32, 64);

        attackUp = attackDown = attackLeft = attackRight = false;

        bounds.x = 0;
        bounds.y = 16;
        bounds.width = 32;
        bounds.height = 48;

        loadSprites();
        setSpeed(1f);
        setHealth(2);
    }

    private void loadSprites() {
        //Animations
        animationDown = new Animation(64, Assets.vampire_down);
        animationUp = new Animation(64, Assets.vampire_up);
        animationLeft = new Animation(64, Assets.vampire_left);
        animationRight = new Animation(64, Assets.vampire_right);
    }


    @Override
    public void tick() {
        //Animations
        animationDown.tick();
        animationUp.tick();
        animationLeft.tick();
        animationRight.tick();

        //Movement
        if(colCircleBox(handler.getPlayer())) {
            diameter = 600;
            moveToPlayer();
            move();
            count = 31;
        } else {
            count++;
            if(count > 40) {
                autoMoveDecider();
                count = 0;
            }
            move();

            diameter = 200;
        }
    }

    @Override
    public void die(){
        System.out.println("Ogre has been slain");
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentAnimationFrame(),
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                width, height, null);

        // Red rectangle to represent collision box
//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);

        // Oval around enemy
        g.setColor(Color.blue);
        g.drawOval((int)(x + width/2 - handler.getGameCamera().getxOffset() - diameter / 2),
                (int)(y + height/2 - handler.getGameCamera().getyOffset() - diameter / 2), diameter, diameter);


        // Rect around player
//        g.setColor(Color.red);
//        g.drawRect((int) (handler.getPlayer().getX() + player.getBounds().x - handler.getGameCamera().getxOffset()),
//                (int) (handler.getPlayer().getY() + player.getBounds().y - handler.getGameCamera().getyOffset()),
//                handler.getPlayer().getBounds().width, handler.getPlayer().getBounds().height);
    }


    public void postRender(Graphics g){

        //Attack animations

        if(attackUp) {
            g.drawImage(Assets.attack,
                    (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset() - (height / 2 + 20)),
                    width, height, null);
            //return Assets.playerIdleDown;
        }
        else if(attackDown) {
            g.drawImage(Assets.attack,
                    (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset() + (height / 2 + 20)),
                    width, height, null);
            //return Assets.playerIdleUp;
        }
        else if(attackLeft) {
            g.drawImage(Assets.attack,
                    (int) (x - handler.getGameCamera().getxOffset() - (width / 2 + 20)),
                    (int) (y - handler.getGameCamera().getyOffset()),
                    width, height, null);
            //return Assets.playerIdleLeft;
        }
        else if(attackRight) {
            g.drawImage(Assets.attack,
                    (int) (x - handler.getGameCamera().getxOffset() + (width / 2 + 20)),
                    (int) (y - handler.getGameCamera().getyOffset()),
                    width, height, null);
            //return Assets.playerIdleRight;
        }
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
                return Assets.vampireIdleDown;
            }
            else if(direction == 1) {
                return Assets.vampireIdleUp;
            }
            else if(direction == 2) {
                return Assets.vampireIdleRight;
            }
            else if(direction == 3) {
                return Assets.vampireIdleLeft;
            }
        }
        return Assets.enemyIdleDown;
    }


    public boolean isAttackUp() {
        return attackUp;
    }

    public void setAttackUp(boolean attackUp) {
        this.attackUp = attackUp;
    }

    public boolean isAttackDown() {
        return attackDown;
    }

    public void setAttackDown(boolean attackDown) {
        this.attackDown = attackDown;
    }

    public boolean isAttackLeft() {
        return attackLeft;
    }

    public void setAttackLeft(boolean attackLeft) {
        this.attackLeft = attackLeft;
    }

    public boolean isAttackRight() {
        return attackRight;
    }

    public void setAttackRight(boolean attackRight) {
        this.attackRight = attackRight;
    }

    public long getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(long attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
