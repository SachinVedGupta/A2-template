package ca.mcmaster.se2aa4.island.teamXXX;

public class Position {
    private int x;
    private int y; 

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position getPosition() {
        return new Position(x, y);
    }
}
