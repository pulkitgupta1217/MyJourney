package main.java.FX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import main.java.Server.ServerHost;

import java.io.IOException;

public class Screen extends StackPane {
    private Button hostButton;
    private HBox hosting;
    private VBox start;
    private TextArea output;
    private TextArea users;
    private ServerHost server;

    public Screen() {
        super();
        start = new VBox(20);
        start.setAlignment(Pos.CENTER);
        output = new TextArea("Starting Server:");
        output.setEditable(false);
        output.setBackground(new Background(new BackgroundFill(Color.rgb(127, 127, 127, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        hostButton = new Button("Host!");
        hostButton.setAlignment(Pos.BOTTOM_CENTER);
        hosting = new HBox(20);
        users = new TextArea("Users");
        users.setEditable(false);
        users.setBackground(new Background(new BackgroundFill(Color.rgb(127, 127, 127, 1), CornerRadii.EMPTY, Insets.EMPTY)));
        hosting.setAlignment(Pos.CENTER);
        hosting.getChildren().add(users);
        hosting.getChildren().add(output);
        hostButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            hostButton.setVisible(false);
            try {
                Screen.this.getChildren().add(hosting);
                server = new ServerHost(output, users);
                server.start();

            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        });
        start.getChildren().add(hostButton);
        getChildren().addAll(start);
    }
    public ServerHost getServerHost() {
        return server;
    }
}
