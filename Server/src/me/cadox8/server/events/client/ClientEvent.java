package me.cadox8.server.events.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.cadox8.server.events.Event;
import me.cadox8.server.socket.ClientThread;

@AllArgsConstructor
public abstract class ClientEvent extends Event {

    @Getter private ClientThread client;
}
