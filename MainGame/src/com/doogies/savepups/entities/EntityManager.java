package com.doogies.savepups.entities;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = (o1, o2) -> {
        //o1 has a lesser y pos than o2
        //Should be above o2 and should be rendered first
        if(o1.getY() + o1.getHeight() < o2.getY() + o2.getHeight()) { //Checking bottom of collision box
            return -1;
        }
        //o1 should be rendered after o2
        return 1;
    };

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        addEntity(player);
    }

    public void tick(){
        Iterator<Entity> iterator = entities.iterator();

        while(iterator.hasNext()) {
            Entity e = iterator.next();
            e.tick();
            if(!e.isActive()){
                iterator.remove();
            }
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g){
        for(Entity e : entities) {
            e.render(g);
        }
        player.postRender(g);
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
