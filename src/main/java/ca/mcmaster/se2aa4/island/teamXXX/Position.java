package ca.mcmaster.se2aa4.island.teamXXX;

public class Position {
    private int x;
    private int y; 
    private TerrainType biome;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        biome = TerrainType.UNKNOWN;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Position getPosition() {
        return new Position(x, y); // avoid leaky abstraction by returning a copy of the position
    }


}
