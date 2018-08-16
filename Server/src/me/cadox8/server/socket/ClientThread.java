package me.cadox8.server.socket;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.commons.Message;
import me.cadox8.server.events.client.ClientConnectEvent;
import me.cadox8.server.events.client.ClientDisconnectEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread {

    private int id;
    @Getter @Setter private boolean dead;

    private String clientName;
    @Getter private Socket clientSocket;
    private final ClientThread[] threads;
    private int maxClientsCount;

    private ObjectOutputStream os;
    private ObjectInputStream is;

    public ClientThread(int id, Socket clientSocket, ClientThread[] threads) {
        this.id = id;
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
        dead = false;

        clientName = String.valueOf(id);
    }

    public void run() {
        int maxClientsCount = this.maxClientsCount;
        ClientThread[] threads = this.threads;

        try {
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            is = new ObjectInputStream(clientSocket.getInputStream());

            synchronized (this) {
                new ClientConnectEvent(this).onEvent();//+1;
            }

            while (true) {
                Message msg = (Message) is.readObject();

                if (msg.getAction().startsWith("quit")) break;

                switch (msg.getAction()) {
                    case "send":

                        break;
                }

                synchronized (this) {
                    System.out.println("<" + id + "> " + msg.buildMsg());
                }
            }
            synchronized (this) {
                new ClientDisconnectEvent(this).onEvent();
                threads[id] = null;
            }
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == this) threads[i] = null;
                }
            }
            is.close();
            os.flush();
            os.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            synchronized (this) {
                new ClientDisconnectEvent(this).onEvent();
                threads[id] = null;
            }
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == this) threads[i] = null;
                }
            }
            try {
                os.flush();
                os.close();
            } catch (IOException ed) { }
        }
    }


    private int getRealClients() {
        int i = 0;

        for (Thread t : threads) if (t != null) i++;
        return i;
    }

    public void sendMessageAll(String action, Object... args) throws IOException {
        for (int i = 0; i < maxClientsCount; i++) {
            if (threads[i] != null && threads[i].clientName != null) sendMessage(i, action, args);
        }
    }

    public void sendMessage(int id, String action, Object... args) throws IOException {
        Message msg = new Message(action, args);
        threads[id].os.writeObject(msg);
        System.out.println("-> " + id + ": " + msg.buildMsg());
    }

    public int getClientID() {
        return id;
    }
}