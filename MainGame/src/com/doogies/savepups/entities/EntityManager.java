package com.doogies.savepups.entities;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.creatures.Player;
import com.doogies.savepups.entities.furniture.Bed;
import com.doogies.savepups.entities.furniture.FurnitureTiles;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class EntityManager {

    private Handler handler;
    private Player player;

    // Furniture
    private Bed bed;


    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = (o1, o2) -> {
        if(o1.getY() + o1.getHeight() < o2.getY() + o2.getHeight()) {
            return -1;
        }
        return 1;
    };

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();

        makeFurniture();
        addEntity(player);
    }

    public void tick(){
        for(int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();
            if(!e.isActive()){
                entities.remove(e);
            }
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g){
        for(Entity e : entities) {
            e.render(g);
        }

    }

    public void makeFurniture() {
        bed = new Bed(handler, 0, 0, 0);
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    // Getters and setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    //
}
