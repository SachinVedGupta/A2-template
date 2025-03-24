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


    
    

}
