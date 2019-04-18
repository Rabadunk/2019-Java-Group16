package com.doogies.savepups.entities.creatures;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.house.Room;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private Room currentRoom;

    //Atacck timmer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        this.currentRoom = handler.getRoom();

        bounds.x = 24;
        bounds.y = 40;
        bounds.width = 16;
        bounds.height = 23;

        //Animations
        animationDown = new Animation(500, Assets.player_down);
        animationUp = new Animation(500, Assets.player_up);
        animationLeft = new Animation(500, Assets.player_left);
        animationRight = new Animation(500, Assets.player_right);
    }

    @Override
    public void tick() {
        //Animations
        animationDown.tick();
        animationUp.tick();
        animationLeft.tick();
        animationRight.tick();

        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        //Attack
        checkAttacks();
    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCooldown){
            return;
        }

        Rectangle playerBounds = getCollisionBounds(0,0);
        Rectangle attackRectangle = new Rectangle();
        int attackRangeSize = 20;
        attackRectangle.width = attackRangeSize;
        attackRectangle.height = attackRangeSize;

        if(handler.getKeyManager().aUp){
            attackRectangle.x = playerBounds.x + playerBounds.width / 2;
            attackRectangle.y = playerBounds.y - attackRangeSize;
        }
        else if(handler.getKeyManager().aDown){
            attackRectangle.x = playerBounds.x + playerBounds.width / 2;
            attackRectangle.y = playerBounds.y + playerBounds.height;
        }
        else if(handler.getKeyManager().aLeft){
            attackRectangle.x = playerBounds.x - attackRangeSize;
            attackRectangle.y = playerBounds.y + playerBounds.height / 2 - attackRangeSize / 2;
        }
        else if(handler.getKeyManager().aRight){
            attackRectangle.x = playerBounds.x + playerBounds.width;
            attackRectangle.y = playerBounds.y + playerBounds.height / 2 - attackRangeSize / 2;
        }
        else{
            return;
        }

        attackTimer = 0;

        for(Entity e : handler.getRoom().getEntityManager().getEntities()){
            if(e.equals(this)){
                continue;
            }
            if(e.getCollisionBounds(0,0).intersects(attackRectangle)){
                e.damage(1);
                return;
            }
        }
    }

    @Override
    public void die(){
        System.out.println("You lose");
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up) {
            yMove = -speed;
        }

        if(handler.getKeyManager().down) {
            yMove = speed;
        }

        if(handler.getKeyManager().left) {
            xMove = -speed;
        }

        if(handler.getKeyManager().right) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(),
                (int)(x - handler.getGameCamera().getxOffset()),
                (int)(y - handler.getGameCamera().getyOffset()),
                width, height,null);


        // Red rectangle to represent players collision box
//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }

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
            return Assets.playerIdle;
        }
        // Can add idle states in else statement later.
    }


}
