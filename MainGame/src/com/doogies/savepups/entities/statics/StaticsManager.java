package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;
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
            case 3:
                insertPotPlant(entityManager, x, y);
                break;
            case 4:
                insertComputer(entityManager, x, y);
                break;
            case 5:
                insertShelfWithPot(entityManager, x, y);
                break;
            case 6:
                insertDrawers(entityManager, x, y);
                break;
            case 7:
                insertCupboard(entityManager, x, y);
                break;
            case 8:
                insertStool(entityManager, x, y);
                break;
            case 9:
                insertWideStool(entityManager, x, y);
                break;
            case 10:
                insertRadio(entityManager, x, y);
                break;
            case 11:
                insertTv(entityManager, x, y);
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

    public void insertPotPlant(EntityManager entityManager, int spawnX, int spawnY) {
        PotPlant potPlant = new PotPlant(handler, spawnX, spawnY);
        entityManager.addEntity(potPlant);
    }

    public void insertComputer(EntityManager entityManager, int spawnX, int spawnY) {
        Computer computer = new Computer(handler, spawnX, spawnY);
        entityManager.addEntity(computer);
    }

    public void insertShelfWithPot(EntityManager entityManager, int spawnX, int spawnY) {
        ShelfWithPot shelfWithPot = new ShelfWithPot(handler, spawnX, spawnY);
        entityManager.addEntity(shelfWithPot);
    }

    public void insertDrawers(EntityManager entityManager, int spawnX, int spawnY) {
        Drawers drawer = new Drawers(handler, spawnX, spawnY);
        entityManager.addEntity(drawer);
    }

    public void insertCupboard(EntityManager entityManager, int spawnX, int spawnY) {
        Cupboard cupboard = new Cupboard(handler, spawnX, spawnY);
        entityManager.addEntity(cupboard);
    }

    public void insertStool(EntityManager entityManager, int spawnX, int spawnY) {
        Stool stool = new Stool(handler, spawnX, spawnY);
        entityManager.addEntity(stool);
    }

    public void insertWideStool(EntityManager entityManager, int spawnX, int spawnY) {
        WideStool wideStool = new WideStool(handler, spawnX, spawnY);
        entityManager.addEntity(wideStool);
    }

    public void insertRadio(EntityManager entityManager, int spawnX, int spawnY) {
        Radio radio = new Radio(handler, spawnX, spawnY);
        entityManager.addEntity(radio);
    }

    public void insertTv(EntityManager entityManager, int spawnX, int spawnY) {
        Tv tv = new Tv(handler, spawnX, spawnY);
        entityManager.addEntity(tv);
    }
}
