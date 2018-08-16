package me.cadox8.server.events;

public abstract class Event {

    private String name;

    public abstract void onEvent();

    public String getEventName() {
        if (name == null) name = getClass().getSimpleName();
        return name;
    }
}
