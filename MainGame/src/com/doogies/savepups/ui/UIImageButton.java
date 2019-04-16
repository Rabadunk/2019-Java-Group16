package com.doogies.savepups.ui;

import com.doogies.savepups.input.KeyListener;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private KeyListener enter;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, KeyListener enter) {
        super(x, y, width, height);
        this.images = images;
        this.enter = enter;
    }

    @Override
    public void tick() {
        if(this.isSelected()) {
            onEnter();
        }

        this.setSelected(false);
    }

    @Override
    public void render(Graphics g) {
        if(selected) {
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        } else {
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onEnter() {
        enter.onEnter();
    }

}
