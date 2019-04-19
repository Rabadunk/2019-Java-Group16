package com.doogies.savepups.entities.furniture;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
import com.doogies.savepups.entities.EntityManager;

public class FurnitureManager {

    Handler handler;

    public FurnitureManager(Handler handler) {
        this.handler = handler;
    }


    public void insertFurniture(EntityManager entityManager, int ID, int x, int y) {
        switch (ID) {
            case 0:
                insertBed(entityManager, x, y);
                break;
        }
    }

    public void insertBed(EntityManager entityManager, int spawnX, int spawnY) {
        Bed bed = new Bed(handler, spawnX, spawnY);
        entityManager.addEntity(bed);
    }

}
