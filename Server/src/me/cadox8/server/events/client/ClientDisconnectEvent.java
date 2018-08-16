package me.cadox8.server.events.client;

import me.cadox8.server.socket.ClientThread;

public class ClientDisconnectEvent extends ClientEvent {

    public ClientDisconnectEvent(ClientThread client) {
        super(client);
    }

    @Override
    public void onEvent() {
        System.out.println(getClient().getClientID() + " has just disconnected");
    }
}
