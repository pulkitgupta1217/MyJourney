package main.java.View;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.java.Model.Coordinate;

public class HitGrid extends Grid {
    private ShipGrid target;
    public HitGrid() {
        super();
    }

    public void setTarget(ShipGrid target) {
        this.target = target;
    }

    public void attack(Coordinate coordinate) {
        boolean hit = target.receiveAttack(coordinate);
        add(new Rectangle(48, 48, (hit ? Color.RED : Color.WHITE)), coordinate.getX(), coordinate.getY());
        /*if (hit) {
            add(new Rectangle(48, 48, Color.rgb(255, 0, 0, 0.5)), coordinate.getX(), coordinate.getY());
        } else {
            add(new Rectangle(48, 48, Color.rgb(255, 255, 255, 0.5)), coordinate.getX(), coordinate.getY());
        }*/
    }
}
