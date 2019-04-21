package com.doogies.savepups.entities.creatures;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Creature {

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private boolean attackUp, attackDown, attackLeft, attackRight;

    // Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    // Player Direction
    // 0 = down, 1 = up, 2 = left, 3 = right
    private int direction = 0;

    private int diameter =  150;

    public Enemy(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        attackUp = attackDown = attackLeft = attackRight = false;

        bounds.x = 24;
        bounds.y = 40;
        bounds.width = 16;
        bounds.height = 23;

        //Animations
        animationDown = new Animation(500, Assets.enemy_down);
        animationUp = new Animation(500, Assets.enemy_up);
        animationLeft = new Animation(500, Assets.enemy_left);
        animationRight = new Animation(500, Assets.enemy_right);

        setSpeed(0.5f);
        setHealth(4);
    }

    @Override
    public void tick() {
        //Animations
        animationDown.tick();
        animationUp.tick();
        animationLeft.tick();
        animationRight.tick();

        //Movement
        if( colCircleBox(handler.getPlayer())) {
            getInput(handler.getPlayer());
            move();
        } else {
            dontMove();
        }
    }

    public void dontMove() {
        yMove = 0;
        xMove = 0;
    }



    @Override
    public void die(){
        System.out.println("Enemy has been slain");
    }

    private void getInput(Player player) {

        if(y > player.getY() + 1) {
            yMove = -speed;
            direction = 1;
        }

        if(y < player.getY() - 1) {
            yMove = speed;
            direction = 0;
        }

        if(x > player.getX() + 1) {
            xMove = -speed;
            direction = 2;
        }

        if(x < player.getX() - 1) {
            xMove = speed;
            direction = 3;
        }

        float dx = (x + width/2 - handler.getGameCamera().getxOffset()) - (player.getX() - handler.getGameCamera().getxOffset() + player.getWidth() / 2);

        float dy = (y + height/2 - handler.getGameCamera().getyOffset()) - (player.getY() - handler.getGameCamera().getyOffset() + player.getHeight() / 2);

        if((Math.sqrt(dx*dx + dy * dy) < 0.5 * Tile.TILEWIDTH)) {
            dontMove();
        }

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

        // Rect around player
        g.setColor(Color.red);
        g.drawRect((int) (handler.getPlayer().getX() - handler.getGameCamera().getxOffset()), (int) (handler.getPlayer().getY() - handler.getGameCamera().getyOffset()), handler.getPlayer().getWidth(), handler.getPlayer().getHeight());
    }

    public boolean colCircleBox(Player player) {

        float dx = (x + width/2 - handler.getGameCamera().getxOffset()) - (player.getX() - handler.getGameCamera().getxOffset() + player.getWidth() / 2);

        float dy = (y + height/2 - handler.getGameCamera().getyOffset()) - (player.getY() - handler.getGameCamera().getyOffset() + player.getHeight() / 2);

        if(Math.sqrt(dx * dx + dy * dy) < (diameter /2 + player.getWidth() /2)) {
            return true;
        }

        return false;
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
                return Assets.enemyIdleDown;
            }
            else if(direction == 1) {
                return Assets.enemyIdleUp;
            }
            else if(direction == 2) {
                return Assets.enemyIdleLeft;
            }
            else if(direction == 3) {
                return Assets.enemyIdleRight;
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