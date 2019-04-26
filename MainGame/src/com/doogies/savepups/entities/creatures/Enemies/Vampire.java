package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Vampire extends Enemy {

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private BufferedImage idleDown, idleUp, idleLeft, idleRight;
    private boolean attackUp, attackDown, attackLeft, attackRight;
    private boolean isBat, humanSpritesNotLoaded, batSpritesNotLoaded = false;
    private int switchCount = 0;

    // Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    // Player Direction
    // 0 = down, 1 = up, 2 = left, 3 = right


    public Vampire(Handler handler, float x, float y) {
        super(handler, x, y, 80, 80);

        attackUp = attackDown = attackLeft = attackRight = false;

        bounds.x = 4;
        bounds.y = 48;
        bounds.width = 36;
        bounds.height = 32;

        loadHumanSprites();
        setSpeed(1f);
        setHealth(20);
    }

    private void loadHumanSprites() {
        //Animations
        animationDown = new Animation(64, Assets.vampire_down);
        animationUp = new Animation(64, Assets.vampire_up);
        animationLeft = new Animation(64, Assets.vampire_left);
        animationRight = new Animation(64, Assets.vampire_right);
        idleDown = Assets.vampireIdleDown;
        idleUp = Assets.vampireIdleUp;
        idleLeft = Assets.vampireIdleLeft;
        idleRight = Assets.vampireIdleRight;
        width = 40;
        height = 80;
        isBat = false;
        batSpritesNotLoaded = true;
        humanSpritesNotLoaded = false;
    }

    private void loadBatSprites() {
        //Animations
        animationDown = new Animation(64, Assets.bat_down);
        animationUp = new Animation(64, Assets.bat_up);
        animationLeft = new Animation(64, Assets.bat_left);
        animationRight = new Animation(64, Assets.bat_right);
        idleDown = Assets.batIdleDown;
        idleUp = Assets.batIdleUp;
        idleLeft = Assets.batIdleLeft;
        idleRight = Assets.batIdleRight;
        width = 100;
        height = 100;
        isBat = true;
        batSpritesNotLoaded = false;
        humanSpritesNotLoaded = true;
    }

    private void changeToBat() {
        if(!isBat) {
            System.out.println("Changing to bat");
            loadBatSprites();
        }
    }

    private void changeToHuman() {
        if (isBat) {
            System.out.println("Changing to human");
            loadHumanSprites();
        }
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
            count = 31;
        } else {
            count++;
            if(count > 40) {
                autoMoveDecider();
                count = 0;
            }
            diameter = 200;
        }

        move();

        if(health < 20 &&
            health % 5 == 0) {
            health = health - 1;
            changeToBat();
            switchCount = 1000;
        } else if(switchCount == 0) {
            changeToHuman();
        }

        if(switchCount != 0) {
            System.out.println("Reducing switch count: " + switchCount);
            switchCount = switchCount - 1;
        }

    }

    @Override
    public void die(){
        System.out.println("Vampire has been slain");
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
                return idleDown;
            }
            else if(direction == 1) {
                return idleUp;
            }
            else if(direction == 2) {
                return idleLeft;
            }
            else if(direction == 3) {
                return idleRight;
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

    public void damage(int amount) {
        if(isBat) return;
        else{
            health -= amount;
            if(health <= 0){
                active = false;
                die();
            }
        }
    }
}
