package me.cadox8.cah.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends UIObject {

    private BufferedImage image;

    public UIImage(float x, float y, int width, int height, BufferedImage image) {
        super(x, y, width, height);
        this.image = image;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        if (hovering) {
            g.drawImage(image, (int) x, (int) y, width, height, null);
        } else {
            g.drawImage(image, (int) x, (int) y, width + 20, height + 20, null);
        }
    }

    @Override
    public void onClick() {
    }
}
