package com.doogies.savepups.entities.furniture;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;

public abstract class FurnitureTiles extends Entity{

    protected final int ID;
    public static FurnitureTiles furniture[] = new FurnitureTiles[256];


    public FurnitureTiles(Handler handler, float x, float y, int width, int height, int ID) {
        super(handler, x, y, width, height);
        this.ID = ID;

        // Initialising static variables
        furniture[ID] = this;

    }

    public int getID() {
        return ID;
    }

}
