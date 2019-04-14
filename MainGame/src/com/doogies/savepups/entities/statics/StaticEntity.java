package com.doogies.savepups.entities.statics;

import com.doogies.savepups.Handler;
import com.doogies.savepups.entities.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width,height);
    }

}
