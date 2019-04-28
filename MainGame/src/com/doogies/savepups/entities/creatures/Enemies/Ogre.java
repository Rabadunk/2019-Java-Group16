package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioPlayer;
import com.doogies.savepups.entities.creatures.Creature;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.items.Item;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ogre extends Enemy {

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private boolean attackUp, attackDown, attackLeft, attackRight;

    // Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    // Player Direction
    // 0 = down, 1 = up, 2 = left, 3 = right

    // Audio
    public static AudioPlayer ogre2;



    public Ogre(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        attackUp = attackDown = attackLeft = attackRight = false;

        bounds.x = 16;
        bounds.y = 20;
        bounds.width = 32;
        bounds.height = 43;
        setupAttack();

        // Audio
        ogre2 = new AudioPlayer();

        ogre2.setFile("/soundEffects/rpgSounds/NPC/ogre/ogre2");

        loadSprites();
        setSpeed(1f);
        setHealth(2);
    }

    private void loadSprites() {
        //Animations
        animationDown = new Animation(64, Assets.ogre_left);
        animationUp = new Animation(64, Assets.ogre_right);
        animationLeft = new Animation(64, Assets.ogre_left);
        animationRight = new Animation(64, Assets.ogre_right);
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

//        if(moveToPlayerSound) {
//            ogre2.play();
//            moveToPlayerSound = false;
//        } else if (autoMoveSound) {
//            ogre3.play();
//            autoMoveSound = false;
//        }

        if(attacking){
            ogre2.play();
        }
    }

    @Override
    public void die(){

        basicEnemyDeath();

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
//        else{
//            // 0 = down, 1 = up, 2 = left, 3 = right
//            if(direction == 0) {
//                return Assets.ogreIdleLeft;
//            }
//            else if(direction == 1) {
//                return Assets.ogreIdleRight;
//            }
//            else if(direction == 2) {
//                return Assets.ogreIdleRight;
//            }
//            else if(direction == 3) {
//                return Assets.ogreIdleLeft;
//            }
//        }
        return Assets.ogreIdleLeft;
    }
}
