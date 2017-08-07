package main.java.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.java.Controller.BattleShipController;

public class PlayerView extends VBox {
    private HBox target;
    private HBox barrier;
    private HBox self;
    public PlayerView() {
        super(10);
        setAlignment(Pos.TOP_CENTER);

        //title
        String titleMessage = "Hello " + BattleShipController.getUserSettings().get("username") + "! Welcome to BattleShipFX";
        HBox title = new HBox(10);
        title.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        title.getChildren().add(new Text(titleMessage));
        title.setAlignment(Pos.CENTER);

        //title.setFont(new Font()/* title.getFont()*/);

        //general
        int spacing = 10;
        int gridYSpacing = 32;
        int gridXSpacing = 43;

        //opponent
        target = new HBox(spacing);
        target.setAlignment(Pos.CENTER);
        VBox yLabel = new VBox(gridYSpacing);
        yLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));;
        //yLabel.setAlignment(Pos.CENTER);

        for (int i = 0; i < 8; i++) {
            String letter = Character.toString((char) (i + (int) 'A'));
            yLabel.getChildren().add(new Text(letter));
        }
        yLabel.getChildren().add(new Text(""));
        VBox gridAndLabel = new VBox(spacing);
        HBox xLabel = new HBox(gridXSpacing);
        xLabel.setAlignment(Pos.CENTER);
        for (int i = 1; i <=8; i++) {
            xLabel.getChildren().add(new Text(Integer.toString(i)));
        }
        xLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));;
        Grid targetGrid = new HitGrid();
        gridAndLabel.getChildren().add(targetGrid);
        gridAndLabel.getChildren().add(xLabel);
        target.getChildren().add(yLabel);
        target.getChildren().add(gridAndLabel);

        //barrier
        barrier = new HBox();
        barrier.setAlignment(Pos.CENTER);
        barrier.getChildren().add(new Text("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||"));
        barrier.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));;


        //self
        self = new HBox(spacing);
        self.setAlignment(Pos.CENTER);
        yLabel = new VBox(gridYSpacing);
        for (int i = 0; i < 8; i++) {
            String letter = Character.toString((char) (i + (int) 'A'));
            Text t = new Text(letter);;
            yLabel.getChildren().add(t);
        }
        yLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));;
        gridAndLabel = new VBox(spacing);
        xLabel = new HBox(gridXSpacing);
        xLabel.setAlignment(Pos.CENTER);
        for (int i = 1; i <=8; i++) {
            xLabel.getChildren().add(new Text(Integer.toString(i)));
        }
        xLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));;

        Grid selfGrid = new ShipGrid();
        gridAndLabel.getChildren().add(selfGrid);
        gridAndLabel.getChildren().add(xLabel);
        self.getChildren().add(yLabel);
        self.getChildren().add(gridAndLabel);

        //end
        getChildren().add(title);
        getChildren().add(target);
        getChildren().add(barrier);
        getChildren().add(self);
    }
}
