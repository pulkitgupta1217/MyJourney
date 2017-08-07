package main.java.Server;

public class ServerPacket  {
    private String gameID;
    private String command;

    public ServerPacket(String gameID, String command){
        this.gameID = gameID;
        this.command = command;
    }

    public String getGameID() {
        return gameID;
    }

    public String getCommand() {
        return command;
    }
}
