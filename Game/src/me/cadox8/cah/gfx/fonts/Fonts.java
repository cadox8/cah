package me.cadox8.cah.gfx.fonts;

import java.awt.*;
import java.util.ArrayList;

public class Fonts {

    private static ArrayList<Font> deudFonts;

    public static void init() {
        deudFonts = new ArrayList<>();

        deudFonts.add(FontLoader.loadFont("/fonts/game.ttf", 28)); //0
    }

    public static Font getFont(int id) {
        return deudFonts.get(id);
    }
}
