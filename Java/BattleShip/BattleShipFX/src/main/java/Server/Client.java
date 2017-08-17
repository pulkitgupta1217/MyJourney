package main.java.Server;

import main.java.Controller.BattleShipController;
import main.java.Model.ServerInfo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class Client {
    private int port;
    private String serverIP;
    private String gameID;
    private String username;

    public Client(ServerInfo info) {
        this.port = Integer.parseInt(info.getPort());
        this.serverIP = info.getIp();
        this.gameID = info.getGameID();
        username = BattleShipController.getUsername();


    }
    public String connectToServer() {
        try {
            System.out.println("connecting to server at: " + serverIP + ", on port: " + port + " ..." + System.currentTimeMillis());
            Socket connection = new Socket(serverIP, port);
            System.out.println("just connected to server: " + connection.getRemoteSocketAddress());
            ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(connection.getInputStream());
            out.writeObject(new ServerPacket(gameID, "testConnection: " + username));
            String result = (String) in.readObject();
            connection.close();
            return "Connected!";
        } catch (ConnectException e) {
            System.out.println("couldn't connect");
            e.printStackTrace();
            return "failed";
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return "FailedToConnect";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "GameFiles Changed, have you been hacking?";
        }
    }

}
