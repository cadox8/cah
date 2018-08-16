package me.cadox8.cah.client;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.commons.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    @Getter private ChatAccess access;
    @Getter @Setter private Message serverInfo = null;

    public void connect(String... args) throws IOException {
        String server = "localhost";
        int port = 7639;

        if (args.length > 0 && args[0] != null) server = args[0];
        if (args.length > 1 && args[1] != null) port = Integer.valueOf(args[1]);

        access = new ChatAccess();

        access.InitSocket(server,port);
    }

    public class ChatAccess {
        private Socket socket;
        private ObjectOutputStream outputStream;

        public void InitSocket(String server, int port) throws IOException {
            socket = new Socket(server, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());

            Thread receivingThread = new Thread(()-> {
                try {
                    ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                    serverInfo = ((Message)is.readObject());
                    System.out.println(serverInfo.buildMsg());
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            receivingThread.start();
        }

        public void send(Message msg) {
            try {
                outputStream.writeObject(msg);
                outputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


        public void close() {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
