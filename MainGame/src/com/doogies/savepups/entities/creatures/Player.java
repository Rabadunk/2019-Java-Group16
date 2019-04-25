package com.doogies.savepups.entities.creatures;

import com.doogies.savepups.Handler;
import com.doogies.savepups.audio.AudioPlayer;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.house.Room;
import com.doogies.savepups.hud.GameHud;
import com.doogies.savepups.inventory.Inventory;
import com.doogies.savepups.states.GameState;
import com.doogies.savepups.states.State;
import com.doogies.savepups.utils.GameTimer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    private Room currentRoom;

    // Bed
    private boolean bed = false;

    private Rectangle attackRectangle;
    int attackRangeSize = 40;

    // Animations
    private Animation animationDown, animationUp, animationLeft, animationRight;
    private Animation animationAttackDown, animationAttackUp, animationAttackLeft, animationAttackRight;

    private boolean attackUp, attackDown, attackLeft, attackRight;

    // Attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    // Game hud
    private Inventory inventory;
    private GameHud gameHud;

    // Game Timer
    private GameTimer gameTimer;
    private boolean timerStart = false;
    private boolean timerSet = false;
    private int timeTakenMinutes, timeTakenSeconds = 0;
    private long initalTime;
    private boolean playerActive = false;

    // Score
    private int score = 0;
    private int trackedGoldCoins = 0;
    private int trackedSilverCoins = 0;
    private int trackedCopperCoins = 0;

    // Player Direction
    // 0 = down, 1 = up, 2 = left, 3 = right
    private int direction = 0;

    // Game result
    public static boolean isGameWon = false;


    public Player(Handler handler, float x, float y) {
        super(handler, x, y, 32, 64);
        this.currentRoom = handler.getRoom();
        setupAttack();
        setupBounds();
        loadSprites();
        loadGameUtils();
    }

    public void setupAttack() {
        attackUp = attackDown = attackLeft = attackRight = false;
        attackRectangle = new Rectangle();
        attackRectangle.width = attackRangeSize;
        attackRectangle.height = attackRangeSize;
    }

    public void setupBounds() {
        bounds.x = 0;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;
    }

    public void loadSprites() {
        //Animations
        animationDown = new Animation(90, Assets.player_down);
        animationUp = new Animation(90, Assets.player_up);
        animationLeft = new Animation(80, Assets.player_left);
        animationRight = new Animation(80, Assets.player_right);

        animationAttackDown = new Animation(160, Assets.playerAttackDown);
        animationAttackUp = new Animation(160, Assets.playerAttackUp);
        animationAttackLeft = new Animation(160, Assets.playerAttackLeft);
        animationAttackRight = new Animation(160, Assets.playerAttackRight);
    }

    public void loadGameUtils() {
        // Inventory
        inventory = new Inventory(handler);

        // hud
        gameHud = new GameHud(handler);

        gameTimer = new GameTimer(handler);
    }

    @Override
    public void tick() {
        //Animations
        animationDown.tick();
        animationUp.tick();
        animationLeft.tick();
        animationRight.tick();

        animationAttackDown.tick();
        animationAttackUp.tick();
        animationAttackLeft.tick();
        animationAttackRight.tick();

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

        // Check silly bed function
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_G)){
            bed = !bed;
        }

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_H)){
        }

        testStates();

        // Trackers
        timeTracker();
        scoreTracker();

    }

    public void timeTracker(){

        if(playerActive && !timerSet){
            initalTime = System.currentTimeMillis();
            timerSet = true;
        }

        if(!playerActive) {
            timeTakenMinutes = 0;
            timeTakenSeconds = 0;
        }
        else {
            timeTakenMinutes = (int) (System.currentTimeMillis() - initalTime) / 1000 / 60;
            timeTakenSeconds = (int) ((System.currentTimeMillis() - initalTime) / 1000) % 60;
        }
    }

    public void scoreTracker(){

        if(handler.getPlayer().getInventory().getItem("CoinGold") > trackedGoldCoins) {
            score += 10 * handler.getPlayer().getInventory().getItem("CoinGold");
            trackedGoldCoins++;
        }
        if(handler.getPlayer().getInventory().getItem("CoinSilver") > trackedSilverCoins) {
            score += 5 * handler.getPlayer().getInventory().getItem("CoinSilver");
            trackedSilverCoins++;
        }
        if(handler.getPlayer().getInventory().getItem("CoinCopper") > trackedCopperCoins) {
            score += 1 * handler.getPlayer().getInventory().getItem("CoinCopper");
            trackedCopperCoins++;
        }
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
            playerActive = true;
        }
        // Down
        else if(handler.getKeyManager().attack && direction == 0){
            attackRectangle.x = playerBounds.x + playerBounds.width / 2;
            attackRectangle.y = playerBounds.y + playerBounds.height;
            attackDown = true;
            playerActive = true;
        }
        // Left
        else if(handler.getKeyManager().attack && direction == 2){
            attackRectangle.x = playerBounds.x - attackRangeSize;
            attackRectangle.y = playerBounds.y + playerBounds.height / 2 - attackRangeSize / 2;
            attackLeft = true;
            playerActive = true;
        }
        // Right
        else if(handler.getKeyManager().attack && direction == 3){
            attackRectangle.x = playerBounds.x + playerBounds.width;
            attackRectangle.y = playerBounds.y + playerBounds.height / 2 - attackRangeSize / 2;
            attackRight= true;
            playerActive = true;
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

    public void testStates(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_L)){
            die();
        }

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_M)){
            isGameWon = true;
            State.setState(handler.getGame().gameEndState);
        }
        
    }

    @Override
    public void die(){
        isGameWon = false;
        State.setState(handler.getGame().gameEndState);
    }

    private void getInput() {
        if(inventory.isActive()){
            yMove = 0;
            xMove = 0;
            return;
        }

        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up) {
            yMove = -speed;
            direction = 1;
            playerActive = true;
        }

        if(handler.getKeyManager().down) {
            yMove = speed;
            direction = 0;
            playerActive = true;
        }

        if(handler.getKeyManager().left) {
            xMove = -speed;
            direction = 2;
            playerActive = true;
        }

        if(handler.getKeyManager().right) {
            xMove = speed;
            direction = 3;
            playerActive = true;
        }
    }

    @Override
    public void render(Graphics g) {
        if (getCurrentAnimationFrame() == Assets.bed){
            g.drawImage(getCurrentAnimationFrame(),
                    (int)(x - handler.getGameCamera().getxOffset()) + width / 4,
                    (int)(y - handler.getGameCamera().getyOffset()),
                    width*2, height*2,null);
        }
        else {
            g.drawImage(getCurrentAnimationFrame(),
                    (int) (x - handler.getGameCamera().getxOffset()),
                    (int) (y - handler.getGameCamera().getyOffset()),
                    width, height, null);
        }

    }

    public void postRender(Graphics g){
        gameHud.render(g);
        inventory.render(g);
    }

    // Getters and setters

    public BufferedImage getCurrentAnimationFrame(){
        // Attack Animations
        if(attackUp){
            return animationAttackUp.getCurrentFrame();
        }
        else if(attackDown){
            return animationAttackDown.getCurrentFrame();
        }
        else if(attackLeft){
            return animationAttackLeft.getCurrentFrame();
        }
        else if(attackRight){
            return animationAttackRight.getCurrentFrame();
        }

        // Walking animations
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

        // Idle animations
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

    // Getters and setters

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

    public boolean isPlayerActive() {
        return playerActive;
    }

    public void setPlayerActive(boolean playerActive) {
        this.playerActive = playerActive;
    }

    public int getTimeTakenMinutes() {
        return timeTakenMinutes;
    }

    public void setTimeTakenMinutes(int timeTakenMinutes) {
        this.timeTakenMinutes = timeTakenMinutes;
    }

    public int getTimeTakenSeconds() {
        return timeTakenSeconds;
    }

    public void setTimeTakenSeconds(int timeTakenSeconds) {
        this.timeTakenSeconds = timeTakenSeconds;
    }

    public boolean isTimerSet() {
        return timerSet;
    }

    public void setTimerSet(boolean timerSet) {
        this.timerSet = timerSet;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static boolean isIsGameWon() {
        return isGameWon;
    }

    public static void setIsGameWon(boolean isGameWon) {
        Player.isGameWon = isGameWon;
    }
}
