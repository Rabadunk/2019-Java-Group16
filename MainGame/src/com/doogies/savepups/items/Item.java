package com.doogies.savepups.items;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Animation;
import com.doogies.savepups.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    // Animations

    public static Animation coinGoldAnimation = new Animation(80, Assets.coinGold);
    public static Animation coinSilverAnimation = new Animation(80, Assets.coinSilver);
    public static Animation coinCopperAnimation = new Animation(80, Assets.coinCopper);

    //Handler

    public static Item[] items = new Item[256];
    public static Item bedItem = new Item(Assets.bed, "Bed", 0, false);
    public static Item attackItem = new Item(Assets.attack, "Attack", 1, false);
    public static Item coinGold = new Item(Assets.coinGold[0], "CoinGold", 2, true);
    public static Item coinSilver = new Item(Assets.coinGold[0], "CoinSilver", 3, true);
    public static Item coinCopper = new Item(Assets.coinGold[0], "CoinCopper", 4, true);



    // Class

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;
    protected boolean animated;

    protected Rectangle bounds;

    protected int x, y, count;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id, boolean animated){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;
        this.animated = animated;


        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;


    }

    public void tick(){
        if(handler.getRoom().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds)){
            pickedUp = true;
            handler.getRoom().getEntityManager().getPlayer().getInventory().addItem(this);
        }
        coinGoldAnimation.tick();
        coinSilverAnimation.tick();
        coinCopperAnimation.tick();
    }

    public void render(Graphics g){
        if(handler == null){
            return;
        }

        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y){

        if(this.animated){
            g.drawImage(getCurrentAnimationFrame(), x, y, ITEMWIDTH, ITEMHEIGHT, null);
        }
        else {
            g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
        }

        // Red rectangle to represent players collision box
//        g.setColor(Color.red);
//        g.fillRect((int)(x),
//                (int)(y),
//               bounds.width, bounds.height);

    }

    public BufferedImage getCurrentAnimationFrame(){

        switch(this.id) {
            case 2:
                return coinGoldAnimation.getCurrentFrame();
            case 3:
                return coinSilverAnimation.getCurrentFrame();
            case 4:
                return coinCopperAnimation.getCurrentFrame();
            default:
                return coinGoldAnimation.getCurrentFrame();
        }
    }

    public Item createNew(int count){
        Item i = new Item(texture, name, id, animated);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id, animated);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this .y = y;
        bounds.x = x;
        bounds.y = y;
    }

    // Getters and setters


    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
