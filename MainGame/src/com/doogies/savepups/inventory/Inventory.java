package com.doogies.savepups.inventory;

import com.doogies.savepups.Handler;
import com.doogies.savepups.graphics.Assets;
import com.doogies.savepups.graphics.Text;
import com.doogies.savepups.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    //TEMP CODENMROE CODE

    private int invX = 64, invY = 48,
            invWidth = 512, invHeight = 384,
            invListCenterX = invX + 171,
            invListCenterY = invY + invHeight / 2 + 5,
            invListSpacing = 30;

    private int invImageX = 452, invImageY = 82,
            invImageWidth = 64, invImageHeight = 64;

    private int invCountX = 484, invCountY = 172;

    private int selectedItem = 0;

    public Inventory(Handler handler){
        this.handler = handler;
        inventoryItems = new ArrayList<>();

        addItem(Item.bedItem.createNew(5));
    }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)){
            active = !active;
        }
        if(!active){
            return;
        }

        System.out.println("Inventory: ");
        for(Item i : inventoryItems){
            System.out.println(i.getName() + " " + i.getCount());
        }

//        System.out.println("Inventory working");
    }

    public void render(Graphics g){
        if(!active){
            return;
        }

        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

        Text.drawString(g, "> Bed <", invListCenterX, invListCenterY, true, Color.WHITE, Assets.font28);
    }

    // Inventory methods

    public void addItem(Item item){
        for(Item i: inventoryItems){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    // Getters and setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
