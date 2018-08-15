package me.cadox8.cah;

import me.cadox8.cah.game.Game;

public class Launcher {

    public static final String VERSION = "Alpha v0.5.5";

    public static void main(String... args) {
        Game game = new Game("Cards Against Humanity ~~ " + VERSION, 800, 600);

        game.start();
    }
}
