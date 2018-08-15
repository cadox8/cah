package me.cadox8.cah.gfx.textures;

import me.cadox8.cah.gfx.Sprites;
import me.cadox8.cah.utils.Utils;

import java.awt.image.BufferedImage;

public class GUI {

    public static BufferedImage[] play;
    public static BufferedImage[] exit;
    public static BufferedImage[] background;

    public static void init() {
        final Sprites gui = new Sprites(Utils.loadImage("/textures/gui/gui.png"));

        play = new BufferedImage[2];
        play[0] = gui.crop(0, 0, 93, 35);
        play[1] = gui.crop(0, 35, 93, 35);

        exit = new BufferedImage[2];
        exit[0] = gui.crop(97, 0, 89, 33);
        exit[1] = gui.crop(97, 39, 89, 33);

/*        background = new BufferedImage[1];
        background[0] = Utils.loadImage("/utils/menu.png");*/
    }
}
