package com.doogies.savepups.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images) {
        super(x, y, width, height);
        this.images = images;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(selected) {
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        } else {
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

}
