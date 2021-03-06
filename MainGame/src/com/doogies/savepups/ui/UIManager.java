package com.doogies.savepups.ui;

import com.doogies.savepups.Handler;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class UIManager {

    private Handler handler;
    private ArrayList<UIObject> objects;


    public UIManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<>();
    }

    public void tick() {
        for(UIObject o: objects)
            o.tick();
    }

    public void render(Graphics g) {
        for(UIObject o: objects)
            o.render(g);
    }

    public void onMouseMove(MouseEvent e){
        for(UIObject o: objects)
            o.onMouseMove(e);
        //System.out.println(e.MOUSE_MOVED);
    }

    public void onMouseRelease(MouseEvent e){
        for(UIObject o: objects)
            o.onMouseRelease(e);
    }

    // getters and setters
    public void addObject(UIObject o) {
        objects.add(o);
    }

    public void removeObject(UIObject o) {
        objects.remove(o);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }
}
