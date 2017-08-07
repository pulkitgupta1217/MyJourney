package main.java.Model;

import java.io.Serializable;

public class ServerInfo implements Serializable{
    private String ip;
    private String port;
    private String gameID;

    public ServerInfo(String ip, String port, String gameID) {
        this.ip= ip;
        this.port = port;
        this.gameID = gameID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
}
