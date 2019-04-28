package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioManager;
import com.doogies.savepups.entities.creatures.Creature;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.assets.EnemyAssets;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screamer extends Enemy {

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;

    public Screamer(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        setupBounds();
        setupAttack();
        loadSprites();
        setSpeed(1f);
        setHealth(2);
    }

    private void setupBounds() {
        bounds.x = 16;
        bounds.y = 20;
        bounds.width = 32;
        bounds.height = 43;
    }

    private void loadSprites() {
        //Animations
        animationDown = new Animation(64, EnemyAssets.screamer_left);
        animationUp = new Animation(64, EnemyAssets.screamer_right);
        animationLeft = new Animation(64, EnemyAssets.screamer_left);
        animationRight = new Animation(64, EnemyAssets.screamer_right);
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

        if(attacking){
            AudioManager.scream.play();
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
        return EnemyAssets.screamerIdleLeft;
    }
}
