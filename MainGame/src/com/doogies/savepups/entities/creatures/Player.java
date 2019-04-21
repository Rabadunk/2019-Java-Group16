package com.doogies.savepups.entities.creatures;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioPlayer;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.house.Room;
import com.doogies.savepups.hud.GameHud;
import com.doogies.savepups.inventory.Inventory;
import com.doogies.savepups.states.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    // Bed
    private boolean bed = false;

    private Rectangle attackRectangle;
    int attackRangeSize = 40;

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private Room currentRoom;
    private boolean attackUp, attackDown, attackLeft, attackRight;

    // Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    //
    private Inventory inventory;

    // Game hud
    private GameHud gameHud;

    // TEMP SOUND CODE
    private AudioPlayer sound;

    // Player Direction
    // 0 = down, 1 = up, 2 = left, 3 = right
    private int direction = 0;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        this.currentRoom = handler.getRoom();

        attackUp = attackDown = attackLeft = attackRight = false;

        bounds.x = 24;
        bounds.y = 40;
        bounds.width = 16;
        bounds.height = 23;

        //Animations
        animationDown = new Animation(500, Assets.player_down);
        animationUp = new Animation(500, Assets.player_up);
        animationLeft = new Animation(500, Assets.player_left);
        animationRight = new Animation(500, Assets.player_right);

        // Inventory
        inventory = new Inventory(handler);

        // hud
        gameHud = new GameHud(handler);

        attackRectangle = new Rectangle();
        attackRectangle.width = attackRangeSize;
        attackRectangle.height = attackRangeSize;

        // Temp audio code
        sound = new AudioPlayer();
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

        //Inventory
        inventory.tick();

        // Hud
        gameHud.tick();

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_G)){
            bed = !bed;
//            sound.setFile("song");
//            sound.play();
        }

        //TEST CODE
        testDie();

//        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H)){
//            sound.stop();
//        }


    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCooldown){
            return;
        }

        if(inventory.isActive()){
            return;
        }

        Rectangle playerBounds = getCollisionBounds(0,0);

        attackUp = attackDown = attackLeft = attackRight = false;

        // 0 = down, 1 = up, 2 = left, 3 = right

        // Up
        if(handler.getKeyManager().attack && direction == 1){
            attackRectangle.x = playerBounds.x + playerBounds.width / 2;
            attackRectangle.y = playerBounds.y - attackRangeSize;
            attackUp = true;
        }
        // Down
        else if(handler.getKeyManager().attack && direction == 0){
            attackRectangle.x = playerBounds.x + playerBounds.width / 2;
            attackRectangle.y = playerBounds.y + playerBounds.height;
            attackDown = true;
        }
        // Left
        else if(handler.getKeyManager().attack && direction == 2){
            attackRectangle.x = playerBounds.x - attackRangeSize;
            attackRectangle.y = playerBounds.y + playerBounds.height / 2 - attackRangeSize / 2;
            attackLeft = true;
        }
        // Right
        else if(handler.getKeyManager().attack && direction == 3){
            attackRectangle.x = playerBounds.x + playerBounds.width;
            attackRectangle.y = playerBounds.y + playerBounds.height / 2 - attackRangeSize / 2;
            attackRight= true;
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

    public void testDie(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)){
            this.health = 0;
            die();
            System.out.println("Test");
        }

    }

    @Override
    public void die(){
        State.setState(handler.getGame().gameOverState);
        System.out.println("You lose");
    }

    private void getInput() {
        if(inventory.isActive()){
            return;
        }

        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up) {
            yMove = -speed;
            direction = 1;
        }

        if(handler.getKeyManager().down) {
            yMove = speed;
            direction = 0;
        }

        if(handler.getKeyManager().left) {
            xMove = -speed;
            direction = 2;
        }

        if(handler.getKeyManager().right) {
            xMove = speed;
            direction = 3;
        }
//        if(handler.getKeyManager().boop) {
//            bed = true;
//        }
//        if(handler.getKeyManager().aww) {
//            bed = false;
//        }
    }

    @Override
    public void render(Graphics g) {
        if (getCurrentAnimationFrame() == Assets.bed){
            g.drawImage(getCurrentAnimationFrame(),
                    (int)(x - handler.getGameCamera().getxOffset()) + width / 4,
                    (int)(y - handler.getGameCamera().getyOffset()),
                    width / 2, height,null);
        }
        else {
            g.drawImage(getCurrentAnimationFrame(),
                    (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()),
                    width, height, null);
        }


        //DOesnt work
        // Red rectangle to represent players collision box
        g.setColor(Color.red);
//        g.fillRect((int) (attackRectangle.x - handler.getGameCamera().getxOffset()),
//                (int) (attackRectangle.y - handler.getGameCamera().getyOffset()), attackRangeSize, attackRangeSize);
        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);
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

        gameHud.render(g);
        inventory.render(g);

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
        else if(bed){
            return Assets.bed;
        }
        else{
            // 0 = down, 1 = up, 2 = left, 3 = right
            if(direction == 0) {
                return Assets.playerIdleDown;
            }
            else if(direction == 1) {
                return Assets.playerIdleUp;
            }
            else if(direction == 2) {
                return Assets.playerIdleLeft;
            }
            else if(direction == 3) {
                return Assets.playerIdleRight;
            }
            else{
                // Dunno aye
                return Assets.bed;
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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
