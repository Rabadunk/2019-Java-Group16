package com.doogies.savepups.items;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    //Handler

    public static Item[] items = new Item[256];
    public static Item bedItem = new Item(Assets.bed, "Bed", 0);
    public static Item attackItem = new Item(Assets.attack, "Attack", 1);


    // Class

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    protected int x, y, count;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x, y, ITEMWIDTH / 4, ITEMHEIGHT / 4);

        items[id] = this;
    }

    public void tick(){
        if(handler.getRoom().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds));{
            //pickedUp = true;
            handler.getRoom().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }

    public void render(Graphics g){
        if(handler == null){
            return;
        }

        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);

        // Red rectangle to represent players collision box
        g.setColor(Color.red);
        g.fillRect((int)(x),
                (int)(y),
               bounds.width, bounds.height);
    }

    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
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
