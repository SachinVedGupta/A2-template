package ca.mcmaster.se2aa4.island.teamXXX.drone;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Map {
    private int width;
    private int height;
    private Set<Position> visitedPositions;
    private Position startPosition;
    private List<POIType> creeks;
    private POIType site;
    private TerrainType[][] terrainGrid;


    public Map() {
        this.width = -1;
        this.height = -1;
        this.visitedPositions = new HashSet<>();
        this.creeks = new ArrayList<>();
    }

    public boolean hasWidth() {
        return this.width != -1;
    }
    public boolean hasHeight() {
        return this.height != -1;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setMapDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void addPosition(Position position) {
        visitedPositions.add(position);
    }

    public boolean alreadyVisited(Position position) {
        return visitedPositions.contains(position);
    }

    public Set<Position> getVisitedPositions() {
        return new HashSet<>(visitedPositions);
    }
    
    public boolean isValidPosition(Position position){
        return position.getX() >= 0 && position.getX() < width &&
               position.getY() >= 0 && position.getY() < height;
    }

    public boolean isAtMapBoundary(Position pos) {
        return pos.getX() == 0 || pos.getX() == width - 1 ||
               pos.getY() == 0 || pos.getY() == height - 1;
    }


    public Position getStartPosition() {
        return startPosition;
    }

    public void setStartingPosition(Position pos) {
        this.startPosition = pos;
        addPosition(pos);
    }

    public boolean isAtSartPoistion(Position pos) {
        return pos.equals(startPosition);
    }

    public List<POIType> getCreeks() {
        return creeks;
    }

    public POIType getSite() {
        return site;
    }
    
    private void addCreek(POIType creek) {
        creeks.add(creek);
    }

    private void addSite(POIType site) {
        this.site = site;
    }
    
    public void addPointOfInterest(POIType type) {
        if (type == POIType.CREEK) {
            addCreek(type);
        } else if (type == POIType.EMERGENCY_SITE) {
            addSite(type);
        }
    }

    public boolean hasCreeks() {
        return !creeks.isEmpty();
    }

    public boolean hasSite() {
        return site != null;
    }

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

    public void updateTerrain(Position pos, TerrainType terrain) {
        if (isValidPosition(pos)) {
            terrainGrid[pos.getX()][pos.getY()] = terrain;
        }
    }

    public TerrainType getTerrainAt(Position pos) {
        if (isValidPosition(pos)) {
            return terrainGrid[pos.getX()][pos.getY()];
        }
        return TerrainType.UNKNOWN;
    }
}
