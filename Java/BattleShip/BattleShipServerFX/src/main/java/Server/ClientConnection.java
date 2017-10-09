package main.java.Server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientConnection extends Thread {
    private Socket client;
    private ObjectInputStream oIn;
    private ObjectOutputStream oOut;

    public static int getConnectionCount() {
        return connectionCount;
    }

    private static int connectionCount = 0;
    private int id = connectionCount++;
    //todo: get port
    private static int port = 12345;
    public ClientConnection(InetAddress addr) {
        System.out.println("client: " + id);
        try {
            client = new Socket(addr, port);
        } catch (IOException e) { }
        try {
            oIn = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
            oOut = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
            start();
        } catch (IOException e) {
            try {
                client.close();
            } catch (IOException e2) { }
        }
    }

    public void run() {
        try {
            //do stuff
            Object obj = oIn.read();
            oOut.writeChars("hello");
        } catch (IOException e) {
            System.out.println("exception");
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connectionCount--;
        }
    }
}
