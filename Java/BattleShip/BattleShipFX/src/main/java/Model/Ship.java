package main.java.Model;

import java.util.List;

public class Ship {
    int length;
    List<Coordinate> coordinates;
    public Ship(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void addCoordinate(Coordinate coordinate) {
        coordinates.add(coordinate);
    }

    public boolean attackCoordinate(Coordinate coordinate) {
        if (coordinates.contains(coordinate)) {
            //TODO: graphics
            coordinates.remove(coordinate);
            return true;
        }
        return false;
    }

    public boolean isDead() {
        return coordinates.isEmpty();
    }
}
