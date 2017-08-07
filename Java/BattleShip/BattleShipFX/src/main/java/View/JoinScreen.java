package main.java.View;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.Controller.BattleShipController;
import main.java.Model.CallBack;
import main.java.Model.ServerInfo;

import java.util.HashMap;
import java.util.List;


public class JoinScreen extends Application {
    private CallBack startGame;

    public void start(Stage primaryStage, CallBack startGame) {
        this.startGame = startGame;
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //TODO: incorporate list of saved servers
        List<ServerInfo> servers = BattleShipController.getServerList();
        Button join = new Button("Join New Server!");
        TextArea iP = new TextArea();
        iP.setPromptText("enter ip of server");
        TextArea port = new TextArea();
        port.setPromptText("enter port of server IP");
        TextArea gameID = new TextArea();
        gameID.setPromptText("enter unique game ID");
        join.setOnMouseClicked((MouseEvent e) -> {
            BattleShipController.createClientConnection(iP.getText(), port.getText(), gameID.getText(), () -> startGame.accept());
        });
        primaryStage.setTitle("join a Server!");
        primaryStage.show();
    }
}
