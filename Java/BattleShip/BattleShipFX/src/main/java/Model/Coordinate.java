package main.java.Model;

public class Coordinate implements Comparable<Coordinate>{
    private int x,y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public int compareTo(Coordinate o) {
        return (this == o || (o.getX() == x && o.getY() == y)) ? 0 : -1;
    }
}
