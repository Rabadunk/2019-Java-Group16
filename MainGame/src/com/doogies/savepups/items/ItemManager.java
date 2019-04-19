package com.doogies.savepups.items;

import com.doogies.savepups.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler){
        this.handler = handler;
        items = new ArrayList<>();
    }

    public void tick(){
        Iterator<Item> iterator = items.iterator();

        while(iterator.hasNext()){
            Item i = iterator.next();
            i.tick();
            if(i.isPickedUp()){
                iterator.remove();
            }
        }
    }

    public void render(Graphics g){
        for(Item i : items){
            i.render(g);
        }
    }

    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
    }

    // Getters and setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
