package ca.mcmaster.se2aa4.island.teamXXX.drone;

public class Position {
    private int x;
    private int y; 
    private TerrainType biome;
    private POIType poi;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        biome = TerrainType.UNKNOWN;
        poi = null;
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
    public boolean isWater() {
        return biome == TerrainType.OCEAN;
    }
    public boolean isLand() {
        return biome != TerrainType.OCEAN && biome != TerrainType.UNKNOWN;
    }
    public void setBiome(TerrainType biome) {
        this.biome = biome;
    }

    


    @Override
    public String toString() { //Debugging purposes
        return "(" + x + ", " + y + ")";
    }
}
