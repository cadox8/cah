package me.cadox8.server;

import lombok.Getter;
import me.cadox8.server.events.server.ServerStartedEvent;
import me.cadox8.server.socket.ClientThread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    @Getter private static final String version = "v0.4.0";

    @Getter private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;

    private static final int maxClientsCount = 10;
    private static final ClientThread[] threads = new ClientThread[maxClientsCount];

    @Getter private static ArrayList<Integer> ids;

    public static void main(String args[]) {
        int portNumber = 7639;

        if (args.length > 0 && args[0] != null) portNumber = Integer.valueOf(args[0]);

        ids = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new ServerStartedEvent().log();

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                int i;
                for (i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new ClientThread(i, clientSocket, threads)).start();
                        break;
                    }
                }
                if (i == maxClientsCount) {
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Server is full. Try later.");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}