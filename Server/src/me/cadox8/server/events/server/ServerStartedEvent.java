package me.cadox8.server.events.server;

import me.cadox8.server.Server;

public class ServerStartedEvent extends ServerEvent {

    @Override
    public void log() {
        System.out.println("Server Started with IP: " + Server.getServerSocket().getLocalSocketAddress());
        System.out.println("Cards Against Humanity server version: " + Server.getVersion());
        System.out.println("Listening for clients...\n");
    }
}
