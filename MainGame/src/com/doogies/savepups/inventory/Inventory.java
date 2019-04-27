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

        // TEMP CODE LOL
//        addItem(Item.bedItem.createNew(10));
//        addItem(Item.bedItem.createNew(5));

    }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)){
            active = !active;
        }
        if(!active){
            return;
        }

        // Inventory inputs
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)|| handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
            selectedItem--;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)|| handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)){
            selectedItem++;
        }

        if(selectedItem < 0){
            selectedItem = inventoryItems.size() - 1;
        }
        else if(selectedItem >= inventoryItems.size()){
            selectedItem = 0;
        }
//        System.out.println("Inventory: ");
//        for(Item i : inventoryItems){
//            System.out.println(i.getName() + " " + i.getCount());
//        }

    }

    public void render(Graphics g){
        if(!active){
            return;
        }

        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);

        int len = inventoryItems.size();
        if(len == 0){
            return;
        }

        for(int i = -5; i < 6; i++){
            if(selectedItem + i < 0 || selectedItem + i >= len){
                continue;
            }
            if(i == 0){
                Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <",
                        invListCenterX, invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.font28);
            }
            else {
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(),
                        invListCenterX, invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.font28);
            }
        }

        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX, invImageY, invImageWidth, invImageHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.font28);

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

    public int getItem(String name){
        for(Item i: inventoryItems){
            if(i.getName() == name){
                return i.getCount();
            }
        }
        return 0;
    }

    public boolean hasItem(String name){
        for(Item i: inventoryItems){
            if(i.getName() == name){
                return true;
            }
        }
        return false;
    }


    // Getters and setters


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Item> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(ArrayList<Item> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }
}