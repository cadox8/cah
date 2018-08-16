package me.cadox8.cah.api;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.cah.client.Client;
import me.cadox8.cah.game.Game;
import me.cadox8.cah.input.MouseManager;

public class CAHAPI {

    @Getter @Setter private Game game;

    @Getter @Setter private boolean debug = false;

    public CAHAPI(Game game) {
        this.game = game;
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public Client getClient() {
        return game.getClient();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }
}
