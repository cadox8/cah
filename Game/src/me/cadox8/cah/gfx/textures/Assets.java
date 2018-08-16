package me.cadox8.cah.gfx.textures;

import me.cadox8.cah.gfx.Sprites;
import me.cadox8.cah.utils.Utils;

import java.awt.image.BufferedImage;

public class Assets {

    public static final int SIZE = 235;
    private static final int width = 239, height = 239;
    private static Sprites sprites;

    // Cards
    public static BufferedImage white, black;

    public static void init() {
        sprites = new Sprites(Utils.loadImage("/textures/assets/basic.png"));

        white = getImage(0, 0);
        black = getImage(1, 0);
    }

    private static BufferedImage getImage(int x, int y) {
        return getImage(x, y, width, height);
    }

    private static BufferedImage getImage(int x, int y, int width, int height) {
        return sprites.crop(width * x, height * y, width, height);
    }
}
