package main.java.Model;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;



public class HitMarker extends Rectangle {
    private static Paint base = Color.rgb(127, 127, 127, 0.5);
    private static Paint miss = Color.rgb(255, 255, 255, 0.5);
    private static Paint hit = Color.rgb(255, 0, 0, 0.75);
    private static Paint hovered = Color.rgb(0, 200, 200, 0.5);
    private static Paint prev = base;

    public HitMarker() {
        super(48, 48, base);
        addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            prev = this.getFill();
            setFill(hovered);
        });
        addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            setFill(prev);
        });
    }

    public void hit() {
        setFill(hit);
    }

    public void miss() {
        setFill(miss);
    }
}
