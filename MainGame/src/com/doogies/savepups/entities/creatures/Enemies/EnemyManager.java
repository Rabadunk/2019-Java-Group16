package com.doogies.savepups.entities.creatures.Enemies;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.EntityManager;

public class EnemyManager {

    Handler handler;

    public EnemyManager(Handler handler) {
        this.handler = handler;
    }

    public void insertEnemies(EntityManager entityManager, int ID, int x, int y) {
        switch (ID) {
            case 0:
                insertOgre(entityManager, x, y);
                break;
            case 1:
                insertOrc(entityManager, x, y);
                break;
            case 2:
                insertOrphan(entityManager, x, y);
                break;
            case 3:
                insertScreamer(entityManager, x, y);
                break;
            case 4:
                insertVampire(entityManager, x, y);
        }
    }

    public void insertOgre(EntityManager entityManager, int spawnX, int spawnY) {
        Ogre ogre = new Ogre(handler, spawnX, spawnY);
        entityManager.addEntity(ogre);
    }

    public void insertOrc(EntityManager entityManager, int spawnX, int spawnY) {
        Orc orc = new Orc(handler, spawnX, spawnY);
        entityManager.addEntity(orc);
    }

    public void insertOrphan(EntityManager entityManager, int spawnX, int spawnY) {
        Orphan orphan = new Orphan(handler, spawnX, spawnY);
        entityManager.addEntity(orphan);
    }

    public void insertScreamer(EntityManager entityManager, int spawnX, int spawnY) {
        Screamer screamer = new Screamer(handler, spawnX, spawnY);
        entityManager.addEntity(screamer);
    }

    public void insertVampire(EntityManager entityManager, int spawnX, int spawnY) {
        Vampire vampire = new Vampire(handler, spawnX, spawnY);
        entityManager.addEntity(vampire);
    }
}
