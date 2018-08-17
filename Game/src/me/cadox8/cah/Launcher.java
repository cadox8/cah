package me.cadox8.cah;

import me.cadox8.cah.data.DataManager;
import me.cadox8.cah.game.Game;

import java.io.File;

public class Launcher {

    public static final String VERSION = "Alpha v0.5.5";
    public static final String GAME_FILE = "C:" + File.separator + "CAH" + File.separator;

    public static void main(String... args) {
        Game game = new Game("Cards Against Humanity ~~ " + VERSION, 1600, 900);

        DataManager.checkFile();

        game.start();
    }
}
