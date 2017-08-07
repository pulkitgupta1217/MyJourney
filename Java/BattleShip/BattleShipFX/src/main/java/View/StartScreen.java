package main.java.View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.Model.CallBack;

import javax.security.auth.callback.Callback;

public class StartScreen extends Application {
    private CallBack join, host, solo;

    public void start(Stage primaryStage, CallBack join, CallBack host, CallBack solo) {
        this.join = join;
        this.host = host;
        this.solo = solo;
        try {
            start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox screen = new VBox(20);
        screen.setAlignment(Pos.CENTER);
        screen.getChildren().add(new Text("Welcome To BattleShip!!"));
        screen.getChildren().add(new Text("Do you want to host or join a game?"));
        HBox buttons = new HBox(50);
        Button hostButton = new Button("Host!");
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            System.out.println("exited game");
            System.exit(0);
        });
        hostButton.setOnMouseClicked(e -> {
            primaryStage.close();
            host.accept();
        });
        Button joinButton = new Button("Join!");
        joinButton.setOnMouseClicked(e -> {
            primaryStage.close();
            join.accept();
        });
        Button soloButton = new Button("Play Against AI!");
        soloButton.setOnMouseClicked(e -> {
            primaryStage.close();
            solo.accept();
        });
        buttons.getChildren().addAll(hostButton, joinButton, soloButton);
        buttons.setAlignment(Pos.CENTER);
        screen.getChildren().add(buttons);
        primaryStage.setScene(new Scene(screen, 400, 200));
        primaryStage.show();
//todo:        screen.setBackground(new Background(new BackgroundImage(new Image("dir"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
