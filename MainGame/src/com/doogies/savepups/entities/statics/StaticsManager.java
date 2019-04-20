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
        }
    }

    public void insertBed(EntityManager entityManager, int spawnX, int spawnY) {
        Bed bed = new Bed(handler, spawnX, spawnY);
        entityManager.addEntity(bed);
    }

}
