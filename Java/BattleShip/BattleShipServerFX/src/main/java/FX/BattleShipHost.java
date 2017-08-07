package main.java.FX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class BattleShipHost extends Application {
    //variables

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("BattleShipFX server");
        Screen screen = new Screen();
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
            try {
                screen.getServerHost().close();
            } catch (NullPointerException npe) {
                System.exit(0);
            }
        });
        primaryStage.setScene(new Scene(screen, 800, 800));
        primaryStage.show();
        //primaryStage.toBack();
    }

    public static void main(String[] args) {
        launch(args);
    }



}
