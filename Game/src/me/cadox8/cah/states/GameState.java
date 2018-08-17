package me.cadox8.cah.states;

import me.cadox8.cah.api.CAHAPI;
import me.cadox8.cah.ui.UIManager;

import java.awt.*;

public class GameState extends State {

    private UIManager uiManager;

    public GameState(CAHAPI API) {
        super(API);

        uiManager = new UIManager(API);
        API.getMouseManager().setUIManager(uiManager);
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
