package main.java.View;


import main.java.Model.Coordinate;
import main.java.Model.Ship;

import java.util.ArrayList;
import java.util.List;

public class ShipGrid extends Grid {
    private List<Ship> ships;
    private List<Coordinate> coordinates;
    public ShipGrid() {
        super();
        ships = new ArrayList<>(5);
        coordinates = new ArrayList<>(17);
    }

    public void addShip(Ship added, int row, int col, int dir) {
        ships.add(added);
        int len = added.getLength();
        int start = dir%2==0 ? row : col;
        int mult = (dir>2 ? 1 : -1);
        for (int i = start; i < start + mult*len; start+=mult) {
            added.addCoordinate(new Coordinate(dir%2==0 ? row : i, dir%2==0 ? i : col));
        }
    }

    public boolean receiveAttack(Coordinate coordinate) {
        for (Ship s: ships) {
            if (s.attackCoordinate(coordinate)) {
                if (s.isDead()) {
                    //TODO: animation
                    ships.remove(s);
                }
                return true;
            }
        }
        return false;
    }
}
