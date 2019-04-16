package com.doogies.savepups.ui;

import com.doogies.savepups.input.ClickListener;
import com.doogies.savepups.input.KeyListener;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private KeyListener enter;
    private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, KeyListener enter, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.enter = enter;
        this.clicker = clicker;
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
        if(selected || hovering ) {
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        } else {
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onEnter() {
        enter.onEnter();
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }

}
