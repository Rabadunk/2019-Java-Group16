package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.EntityManager;

public class StaticsManager {

    Handler handler;

    public StaticsManager(Handler handler) {
        this.handler = handler;
    }

    public void insertStatics(EntityManager entityManager, int ID, int x, int y) {
        switch (ID) {
            case 0:
                insertBed(entityManager, x, y);
                break;
            case 1:
                insertBarrel1(entityManager, x, y);
                break;
            case 2:
                insertBarrel2(entityManager, x, y);
                break;
        }
    }

    public void insertBed(EntityManager entityManager, int spawnX, int spawnY) {
        Bed bed = new Bed(handler, spawnX, spawnY);
        entityManager.addEntity(bed);
    }

    public void insertBarrel1(EntityManager entityManager, int spawnX, int spawnY) {
        Barrel1 barrel1 = new Barrel1(handler, spawnX, spawnY);
        entityManager.addEntity(barrel1);
    }

    public void insertBarrel2(EntityManager entityManager, int spawnX, int spawnY) {
        Barrel2 barrel2 = new Barrel2(handler, spawnX, spawnY);
        entityManager.addEntity(barrel2);
    }
}
