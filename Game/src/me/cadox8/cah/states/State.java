package me.cadox8.cah.states;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.cah.api.CAHAPI;

import java.awt.*;

public abstract class State {

    protected CAHAPI API;

    @Getter @Setter private static State state = null;

    public State(CAHAPI API) {
        this.API = API;
    }


    public abstract void tick();
    public abstract void render(Graphics g);
}
