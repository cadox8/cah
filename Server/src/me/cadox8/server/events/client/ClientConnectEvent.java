package me.cadox8.server.events.client;

import me.cadox8.server.socket.ClientThread;

public class ClientConnectEvent extends ClientEvent {

    public ClientConnectEvent(ClientThread client) {
        super(client);
    }

    @Override
    public void onEvent() {
        System.out.println(getClient().getClientID() + " has just connected");
        System.out.println(getClient().getClientSocket().getInetAddress());
    }
}
