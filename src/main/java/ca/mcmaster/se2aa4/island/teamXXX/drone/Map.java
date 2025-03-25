package ca.mcmaster.se2aa4.island.teamXXX.drone;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents the island map being explored by the drone.
 * Tracks visited positions, terrain types, and points of interest.
 */
public class Map {
    private int width;                         // Width of the map
    private int height;                        // Height of the map
    private Set<Position> visitedPositions;    // Set of positions visited by the drone
    private Position startPosition;            // Starting position of the drone
    private List<POIType> creeks;              // List of discovered creeks
    private POIType site;                      // The emergency site
    private TerrainType[][] terrainGrid;       // Grid of terrain types

    /**
     * Creates a new map with default values.
     * Initially, the map dimensions are unknown (-1).
     */
    public Map() {
        this.width = -1;
        this.height = -1;
        this.visitedPositions = new HashSet<>();
        this.creeks = new ArrayList<>();
    }

    /**
     * Checks if the map has a defined width.
     */
    public boolean hasWidth() {
        return this.width != -1;
    }
    
    /**
     * Checks if the map has a defined height.
     */
    public boolean hasHeight() {
        return this.height != -1;
    }
    
    /**
     * Returns the width of the map.
     */
    public int getWidth() {
        return this.width;
    }
    
    /**
     * Returns the height of the map.
     */
    public int getHeight() {
        return this.height;
    }
    
    /**
     * Sets the width of the map.
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Sets the height of the map.
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Sets both width and height dimensions of the map.
     */
    public void setMapDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    /**
     * Adds a position to the set of visited positions.
     */
    public void addPosition(Position position) {
        visitedPositions.add(position);
    }

    /**
     * Checks if a position has already been visited.
     */
    public boolean alreadyVisited(Position position) {
        return visitedPositions.contains(position);
    }

    /**
     * Returns a copy of the set of visited positions.
     */
    public Set<Position> getVisitedPositions() {
        return new HashSet<>(visitedPositions);
    }
    
    /**
     * Checks if a position is within the bounds of the map.
     */
    public boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
               position.getY() >= 0 && position.getY() < height;
    }

    /**
     * Checks if a position is at the boundary of the map.
     */
    public boolean isAtMapBoundary(Position pos) {
        return pos.getX() == 0 || pos.getX() == width - 1 ||
               pos.getY() == 0 || pos.getY() == height - 1;
    }

    /**
     * Returns the starting position of the drone.
     */
    public Position getStartPosition() {
        return startPosition;
    }

    /**
     * Sets the starting position of the drone and marks it as visited.
     */
    public void setStartingPosition(Position pos) {
        this.startPosition = pos;
        addPosition(pos);
    }

    /**
     * Checks if a position is the starting position.
     */
    public boolean isAtSartPoistion(Position pos) {
        return pos.equals(startPosition);
    }

    /**
     * Returns the list of discovered creeks.
     */
    public List<POIType> getCreeks() {
        return creeks;
    }

    /**
     * Returns the emergency site, if found.
     */
    public POIType getSite() {
        return site;
    }
    
    /**
     * Adds a creek to the list of discovered creeks.
     */
    private void addCreek(POIType creek) {
        creeks.add(creek);
    }

    /**
     * Sets the emergency site.
     */
    private void addSite(POIType site) {
        this.site = site;
    }
    
    /**
     * Adds a point of interest to the map (creek or emergency site).
     */
    public void addPointOfInterest(POIType type) {
        if (type == POIType.CREEK) {
            addCreek(type);
        } else if (type == POIType.EMERGENCY_SITE) {
            addSite(type);
        }
    }

    /**
     * Checks if any creeks have been discovered.
     */
    public boolean hasCreeks() {
        return !creeks.isEmpty();
    }

    /**
     * Checks if the emergency site has been discovered.
     */
    public boolean hasSite() {
        return site != null;
    }

    /**
     * Finds the creek closest to the emergency site.
     * Returns null if either no creeks or no site has been found.
     */
    public POIType getClosestCreek() {
        if (!hasCreeks()) {
            return null;
        }
        if (!hasSite()) {
            return null;
        }
        
        POIType closestCreek = null;
        double minDistance = -1;

        for (POIType creek : creeks) {
            double distance = creek.getPosition().distanceTo(site.getPosition());
            if (distance < minDistance) {
                minDistance = distance;
                closestCreek = creek;
            }
        }
        return closestCreek;
    }
    
    /**
     * Initializes the terrain grid with UNKNOWN terrain.
     * Should be called once map dimensions are known.
     */
    public void initializeGrid() {
        if (width > 0 && height > 0) {
            terrainGrid = new TerrainType[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    terrainGrid[i][j] = TerrainType.UNKNOWN;
                }
            }
        }
    }

    /**
     * Updates the terrain type at a specific position.
     */
    public void updateTerrain(Position pos, TerrainType terrain) {
        if (isValidPosition(pos)) {
            terrainGrid[pos.getX()][pos.getY()] = terrain;
        }
    }

    /**
     * Gets the terrain type at a specific position.
     * Returns UNKNOWN if position is invalid.
     */
    public TerrainType getTerrainAt(Position pos) {
        if (isValidPosition(pos)) {
            return terrainGrid[pos.getX()][pos.getY()];
        }
        return TerrainType.UNKNOWN;
    }
    
    /**
     * Marks a position as visited and updates its terrain if known.
     * Used when the drone moves to a new position.
     */
    public void markVisited(Position position) {
        if (isValidPosition(position)) {
            visitedPositions.add(position);
            
            if (terrainGrid != null && position.getBiome() != TerrainType.UNKNOWN) {
                updateTerrain(position, position.getBiome());
            }
        }
    }
}
