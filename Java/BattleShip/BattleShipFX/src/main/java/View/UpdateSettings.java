package main.java.View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.Controller.BattleShipController;
import main.java.Model.CallBack;

import java.util.HashMap;

public class UpdateSettings extends Application {
    private Scene updateScene;
    private static HashMap<String, String> currSettings;
    //TODO: user correct settings and series of maps
    private CallBack callBack;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Update Settings");
        VBox entries = new VBox(20);
        entries.setAlignment(Pos.CENTER);
        //add TextFields for other user settings
        //TODO: use maps instead of linear stuff
        TextField usernameField;
        usernameField = new TextField();
        String oldUsername = currSettings.get("username");
        usernameField.setPromptText("username");
        if (oldUsername != null) {
            usernameField.setText(oldUsername);
        }
        entries.getChildren().add(usernameField);
        //finish adding settings
        Button done = new Button("Done!");
        entries.getChildren().add(done);
        done.setOnMouseClicked(event -> {
            if (!usernameField.getText().trim().isEmpty()) {
                String username = usernameField.getText().trim();
                currSettings.put("username", username);
                //TODO: replace with more settings or make large if statement
                if (1 == 2/Integer.parseInt("2")) {
                    BattleShipController.writeUserFieldsToFile();
                    callBack.accept();
                    primaryStage.close();
                }
            }
        });

        primaryStage.setScene(new Scene(entries, 600, 600));
        primaryStage.show();

    }

    public void updateSettings(HashMap<String, String> userSettings, CallBack callBack) {
        currSettings = userSettings;
        this.callBack = callBack;
        //replace with method to start new stage
        Stage updateStage = new Stage();
        try {
            start(updateStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //updateSettings(new HashMap<>());
    }
}
