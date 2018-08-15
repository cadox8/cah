package me.cadox8.cah.game;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.cah.api.CAHAPI;
import me.cadox8.cah.display.Display;
import me.cadox8.cah.gfx.fonts.Fonts;
import me.cadox8.cah.gfx.textures.Assets;
import me.cadox8.cah.gfx.textures.GUI;
import me.cadox8.cah.input.MouseManager;
import me.cadox8.cah.states.GameState;
import me.cadox8.cah.states.MenuState;
import me.cadox8.cah.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    //API
    @Getter private CAHAPI API;

    @Getter private Display display;
    @Getter private int width, height;
    private String title;

    @Getter @Setter private boolean running = false;
    private Thread thread;

    // Graphics
    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    public State menuState;

    // Input
    @Getter private MouseManager mouseManager;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;

        mouseManager = new MouseManager();
    }

    private void init() {
        display = new Display(title, width, height);

        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseWheelListener(mouseManager);

        API = new CAHAPI(this);

        Fonts.init();
        Assets.init();
        GUI.init();

        gameState = new GameState(API);
        menuState = new MenuState(API);

        State.setState(menuState);
    }

    private void tick() {
        if (State.getState() != null) State.getState().tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        if (State.getState() != null) State.getState().render(g);

        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                //Log.log("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
