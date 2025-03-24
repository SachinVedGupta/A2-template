package ca.mcmaster.se2aa4.island.teamXXX.drone;
import ca.mcmaster.se2aa4.island.teamXXX.POIType;
import ca.mcmaster.se2aa4.island.teamXXX.Position;

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
        return position.getX()>=0 || position.getX()<width &&
               position.getY()>=0 || position.getY()<height;
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
            double distance = creek.position().distanceTo(site.position());
            if (distance < minDistance) {
                minDistance = distance;
                closestCreek = creek;
            }
        }
        return closestCreek;
    
    }
    


    
    

}
