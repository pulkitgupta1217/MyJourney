package main.java.View;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.java.Model.Coordinate;
import main.java.Model.HitMarker;

import java.util.List;
import java.util.NoSuchElementException;


public abstract class Grid extends GridPane {
    public Grid() {
        super();
        //setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        setHgap(4);
        setVgap(4);
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                /*Rectangle standard = new Rectangle(48, 48, Color.rgb(127, 127, 127, 0.2));
                standard.setOnMouseDragOver(e -> {
                    standard.setFill(Color.rgb(25, 255, 55, 0.2));
                });
                StackPane stack = new StackPane();
                stack.getChildren().add(standard);
                stack.setOnMouseDragEntered(e -> {
                    standard.setFill(Color.rgb(0, 0, 0, 1));
                    System.out.println("starting hover");
                });
                stack.setOnMouseDragExited(e -> {
                    standard.setFill(Color.rgb(127, 127, 127, 0.2));
                    System.out.println("ending hover");
                });
                stack.setOnMouseClicked(e -> {
                    System.out.println("clicked");
                });*/
                HitMarker marker = new HitMarker();
                this.add(marker, r, c);
                if (r == c) {
                    marker.hit();
                } else if (r == 7 - c){
                    marker.miss();
                }

            }
        }
    }

    public Node getChildAdded(Coordinate coordinate) {
        List<Node> children = getChildren();
        for (Node child : children) {
            if (getRowIndex(child) == coordinate.getY() && getColumnIndex(child) == coordinate.getX()) {
                return child;
            }
        }
        throw new NoSuchElementException("no child at coordinate");
    }
}
