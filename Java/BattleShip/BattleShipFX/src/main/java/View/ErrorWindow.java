package main.java.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorWindow extends Application{

    private String error;
    public ErrorWindow(String error) {
        super();
        this.error = error;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Oops! There was an Error");
        TextArea errorText = new TextArea(error);
        errorText.setEditable(false);
        VBox window = new VBox(10);
        Button close = new Button("close");
        close.setOnMouseClicked(e -> {
            primaryStage.close();
        });
        window.getChildren().addAll(errorText, close);
        primaryStage.setScene(new Scene(window, 400, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        //ErrorWindow ew = new ErrorWindow("well crap");
        launch(args);

    }
}
