package main.java.View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.Controller.BattleShipController;
import main.java.Model.CallBack;
import main.java.Server.ServerController;
import main.java.Server.ServerHost;

import java.io.IOException;

public class HostScreen extends Application {

    private static CallBack startGame;
    private Stage primaryStage;

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
        //TODO: make host App that then starts Battleship host and waits for accept from startServer button
        primaryStage.setTitle("BattleShipFX server");
        this.primaryStage = primaryStage;
        ServerPane serverPane = new ServerPane();
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            try {
                System.out.println("shutting down server");
                ServerController.close();
                BattleShipController.close();
            } catch (NullPointerException npe) {
                System.exit(1);
            }
        });
        primaryStage.setScene(new Scene(serverPane, 800, 800));
        primaryStage.show();
        //primaryStage.toBack();
    }

    private class ServerPane extends StackPane {
        private Button hostButton;
        private HBox hosting;
        private VBox start;
        private TextArea output;
        private TextArea users;

        public ServerPane() {
            super();
            start = new VBox(20);
            start.setAlignment(Pos.CENTER);
            hostButton = new Button("Host!");
            hostButton.setAlignment(Pos.BOTTOM_CENTER);
            start.getChildren().add(hostButton);
            hosting = new HBox(20);
            hosting.setAlignment(Pos.CENTER);
            hostButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                System.out.println("starting server");
                hostButton.setVisible(false);
                output = new TextArea("Starting Server:");
                output.setEditable(false);
                output.setBackground(new Background(new BackgroundFill(Color.rgb(127, 127, 127, 1), CornerRadii.EMPTY, Insets.EMPTY)));
                users = new TextArea("Users");
                users.setEditable(false);
                users.setBackground(new Background(new BackgroundFill(Color.rgb(127, 127, 127, 1), CornerRadii.EMPTY, Insets.EMPTY)));
                hosting.getChildren().add(users);
                hosting.getChildren().add(output);
                getChildren().add(hosting);
                try {
                    //todo: create server config menu
                    System.out.println("host screen line 84");
                    ServerController.createServer(output, users);
                    //start game!!
                    //TODO: check actual args for server
                    System.out.println("attempting to create client connection and start game: Hostscreen line 88");
                    if (BattleShipController.createClientConnection(BattleShipController.getDefaultIP(), BattleShipController.getDefaultPort(), BattleShipController.getDefaultGameID(), startGame)) {
                        System.out.println("connected to client: HS.java : 90");
                        primaryStage.toBack();
                    } else {
                        System.out.println("failed to connect to client: HS.java : 93");
                        try {
                            new ErrorWindow("failed to connect to server").start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                }
            });
            getChildren().addAll(start);
        }
    }
}
