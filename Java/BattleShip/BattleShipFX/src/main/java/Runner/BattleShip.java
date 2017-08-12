package main.java.Runner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.Controller.BattleShipController;
import main.java.Model.CallBack;
import main.java.Server.ServerController;
import main.java.View.*;

public class BattleShip extends Application {
    private Stage primary;
    private PlayerView user, opponent;
    private boolean hasOpponent = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            try {
                ServerController.getServer().close();
                BattleShipController.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        BattleShipController.setUp(() -> {
            System.out.println("setup complete");
            CallBack startGame = () -> {
                System.out.println("accepted");
                primary = primaryStage;
                primaryStage.setTitle("BattleShipFX");
                user = new PlayerView();
                if (hasOpponent) {
                    opponent = BattleShipController.getOpponent();
                } else {
                    opponent = new ComputerView();
                }
                HBox game = new HBox(20);
                VBox LPanel = new VBox();
                VBox RPanel = new VBox();
                game.setAlignment(Pos.CENTER);
                game.getChildren().add(LPanel);
                game.getChildren().add(user);
                game.getChildren().add(RPanel);

                //TODO: fix
                //game.setBackground(new Background(new BackgroundImage(new Image("file:\\src\\main\\java\\view\\image\\ocean.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                //System.out.println(game.getBackground().getImages().get(0).getImage().impl_getUrl());
                //primaryStage.setScene(new Scene(, 1920, 1000));

                primaryStage.setScene(new Scene(game, 1920, 1000));
                Image background = new Image("file:resources/image/ocean.png", 1920, 1000, false, false);
                game.setBackground(new Background(new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                primaryStage.show();
            };
            StartScreen startScreen = new StartScreen();
            System.out.println("creating startScreen");
            startScreen.start(new Stage(), () -> {
                //join
                JoinScreen joinScreen = new JoinScreen();
                joinScreen.start(new Stage(), () -> {
                    BattleShipController.startGame(primaryStage, startGame);
                });
            }, () -> {
                //host
                HostScreen hostScreen = new HostScreen();
                hostScreen.start(new Stage(), () -> {
                    BattleShipController.startGame(primaryStage, startGame);
                });

            }, () -> {
                //solo/vsPC
                //TODO: create host in background without showing and use stored Computer AI
                /*TODO: create AI
                * Normal: picks random spots and clusters on hit
                * Hard: picks random spots then follows consecutive hits till sink
                * Impossible: Machine learning algorithm learns the most often used
                *       ship locations and develops attack patterns from there,
                *       may download periodic updates as time progresses in a future form
                **/

            });
        });
//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(System.getProperty("user.home"));

        //BattleShipController.startGame(primaryStage, startGame);

    }
    public static void main(String[] args) {
        launch(args);
    }

}
