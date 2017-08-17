package main.java.Server;

import javafx.scene.control.TextArea;
import main.java.Controller.BattleShipController;
import main.java.Model.CallBack;

import java.io.IOException;

public class ServerController {
    private static ServerHost serverHost;

    public static void createServer(TextArea output, TextArea users, CallBack complete) throws IOException{
        serverHost = new ServerHost(output, users, complete);
        serverHost.start();
    }

    public synchronized static void close() {
        serverHost.close();
    }

    public static ServerHost getServer() {
        return serverHost;
    }
}
