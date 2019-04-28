package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioManager;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.assets.EnemyAssets;
import com.doogies.savepups.states.State;
import com.doogies.savepups.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Vampire extends Enemy {

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private BufferedImage idleDown, idleUp, idleLeft, idleRight;

    private boolean isBat = false;

    public Vampire(Handler handler, float x, float y) {
        super(handler, x, y, 80, 80);

        turnIntoHuman();
        setupAttack();
        setHealth(14);
    }

    private void checkChangeToHuman() {
        if (isBat) {
            System.out.println("Changing to human");
            turnIntoHuman();
        }
    }

    private void turnIntoHuman() {
        loadHumanSprites();
        loadHumanBounds();
        loadHumanAttributes();
    }

    private void loadHumanSprites() {
        //Animations
        animationDown = new Animation(64, EnemyAssets.vampire_down);
        animationUp = new Animation(64, EnemyAssets.vampire_up);
        animationLeft = new Animation(64, EnemyAssets.vampire_left);
        animationRight = new Animation(64, EnemyAssets.vampire_right);
        idleDown = EnemyAssets.vampireIdleDown;
        idleUp = EnemyAssets.vampireIdleUp;
        idleLeft = EnemyAssets.vampireIdleLeft;
        idleRight = EnemyAssets.vampireIdleRight;
    }

    private void loadHumanBounds() {
        bounds.x = 4;
        bounds.y = 48;
        bounds.width = 36;
        bounds.height = 32;
    }

    private void loadHumanAttributes() {
        setSpeed(1f);
        width = 40;
        height = 80;
        isBat = false;
    }

    private void checkChangeToBat() {
        if(!isBat) {
            System.out.println("Changing to bat");
            loadBatSprites();
            spawnOrphans();
        }
    }

    private void loadBatSprites() {
        //Animations
        animationDown = new Animation(64, EnemyAssets.bat_down);
        animationUp = new Animation(64, EnemyAssets.bat_up);
        animationLeft = new Animation(64, EnemyAssets.bat_left);
        animationRight = new Animation(64, EnemyAssets.bat_right);
        idleDown = EnemyAssets.batIdleDown;
        idleUp = EnemyAssets.batIdleUp;
        idleLeft = EnemyAssets.batIdleLeft;
        idleRight = EnemyAssets.batIdleRight;

        loadBatBounds();

        width = 64;
        height = 64;
        isBat = true;
        setSpeed(4f);
    }

    private void loadBatBounds() {
        bounds.x = 24;
        bounds.y = 24;
        bounds.width = 16;
        bounds.height = 16;
    }

    private void spawnOrphans() {
        Orphan orphanOne = new Orphan(handler, 4 * Tile.TILEWIDTH, 4 * Tile.TILEHEIGHT);
        Orphan orphanTwo = new Orphan(handler, 16 * Tile.TILEWIDTH, 4 * Tile.TILEHEIGHT);
        Orphan orphanThree = new Orphan(handler, 4 * Tile.TILEWIDTH, 16 * Tile.TILEHEIGHT);
        Orphan orphanFour = new Orphan(handler, 16 * Tile.TILEWIDTH, 16 * Tile.TILEHEIGHT);
        handler.getRoom().newEntityManager();
        handler.getRoom().getEntityManager().addEntity(this);
        handler.getRoom().getEntityManager().addEntity(orphanOne);
        handler.getRoom().getEntityManager().addEntity(orphanTwo);
        handler.getRoom().getEntityManager().addEntity(orphanThree);
        handler.getRoom().getEntityManager().addEntity(orphanFour);
    }




    @Override
    public void tick() {

        if(attacking){
            AudioManager.bite.play();
        }
        //Animations
        animationDown.tick();
        animationUp.tick();
        animationLeft.tick();
        animationRight.tick();

        glitchCollisionRespawn();

        //Movement
        if(!isBat) {
            moveToPlayer();
            checkAttacks();
            timeTracker();
        } else {
            count++;
            if(count > 20) {
                autoMoveDecider();
                count = 0;
            }
        }

        move();

        if(health < 15 &&
            health % 5 == 0) {
            health = health - 1;
            checkChangeToBat();
        } else if(handler.getRoom().getEntityManager().getEntities().size() < 3) {
            checkChangeToHuman();
        }


    }

    @Override
    public void die(){
        player.isGameWon = true;
        State.setState(handler.getGame().gameEndState);
        System.out.println("Vampire has been slain");
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
        return idleDown;
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
