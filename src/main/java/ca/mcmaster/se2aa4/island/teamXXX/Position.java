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
}
