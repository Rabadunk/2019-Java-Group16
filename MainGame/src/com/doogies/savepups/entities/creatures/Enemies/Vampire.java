package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.states.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Vampire extends Enemy {

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private BufferedImage idleDown, idleUp, idleLeft, idleRight;

    private boolean isBat = false;
    private int switchCount = 0;


    // Direction
    // 0 = down, 1 = up, 2 = left, 3 = right


    public Vampire(Handler handler, float x, float y) {
        super(handler, x, y, 80, 80);

        attackUp = attackDown = attackLeft = attackRight = false;

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

        loadHumanBounds();

        width = 40;
        height = 80;
        isBat = false;
    }

    private void loadHumanBounds() {
        bounds.x = 4;
        bounds.y = 48;
        bounds.width = 36;
        bounds.height = 32;
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

        loadBatBounds();

        width = 64;
        height = 64;
        isBat = true;
    }

    private void loadBatBounds() {
        bounds.x = 16;
        bounds.y = 16;
        bounds.width = 32;
        bounds.height = 32;
    }

    private void changeToBat() {
        if(!isBat) {
            System.out.println("Changing to bat");
            loadBatSprites();

            handler.getRoom();

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
        if(!isBat) {
            moveToPlayer();
        } else {
            count++;
            if(count > 20) {
                autoMoveDecider();
                count = 0;
            }
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
            switchCount = switchCount - 1;
        }

    }

    @Override
    public void die(){
        State.setState(handler.getGame().gameEndState);
        System.out.println("Vampire has been slain");
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentAnimationFrame(),
                (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()),
                width, height, null);

        // Red rectangle to represent collision box
        g.setColor(Color.red);
        g.drawRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);

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
