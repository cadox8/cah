package me.cadox8.cah.states;

import me.cadox8.cah.Launcher;
import me.cadox8.cah.api.CAHAPI;
import me.cadox8.cah.gfx.textures.GUI;
import me.cadox8.cah.ui.UIImageButton;
import me.cadox8.cah.ui.UIManager;

import java.awt.*;
import java.io.IOException;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(CAHAPI API) {
        super(API);

        uiManager = new UIManager(API);
        API.getMouseManager().setUIManager(uiManager);

        //uiManager.addObject(new UIImage(0, 0, 235, 235, Assets.white));

        uiManager.addObject(new UIImageButton(150, 450, 200, 100, GUI.play, () -> {
            API.getMouseManager().setUIManager(null);

            try {
                API.getClient().connect();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }

            setState(API.getGame().gameState);
        }));

        uiManager.addObject(new UIImageButton(450, 450, 200, 100, GUI.exit, () -> System.exit(0)));
    }



    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
        g.setColor(Color.WHITE);
        g.drawString("Version: " + Launcher.VERSION, 5, 595);
        g.drawString("© Cards Against Humanity 2018 - The Game is property of Cadox8", 505, 595);
    }
}
