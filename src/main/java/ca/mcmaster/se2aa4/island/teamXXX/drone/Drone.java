package ca.mcmaster.se2aa4.island.teamXXX.drone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.command.Command;
import ca.mcmaster.se2aa4.island.teamXXX.command.CommandFactory;
import ca.mcmaster.se2aa4.island.teamXXX.command.CommandOption;
import ca.mcmaster.se2aa4.island.teamXXX.results.ScanResult;

/**
 * The Drone class represents the exploration vehicle that navigates the island.
 * It handles movement, scanning, and maintaining its state during the mission.
 */
public class Drone {
    private Direction direction;         // Current heading direction
    private int battery;                // Remaining battery power
    private Position position;          // Current position on the map
    private Map map;                    // Reference to the map being explored
    private CommandFactory commandFactory;  // Factory for creating commands
    private ScanResult prevScanResult;  // Most recent scan results
    private final Logger logger = LogManager.getLogger();

    /**
     * Constructs a new Drone with specified direction, battery, position and map.
     */
    public Drone(Direction direction, int battery, Position position, Map map) {
        this.direction = direction;
        this.battery = battery;
        this.position = position;
        this.map = map;
        this.commandFactory = new CommandFactory();
    }
    
    /**
     * Sets the drone's direction.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    /**
     * Returns the current direction/heading of the drone.
     */
    public Direction getDirection() { //Leaky abstraction, refactor later??
        return direction;
    }

    /**
     * Updates the drone's position and records it in the map.
     */
    public void setPosition(Position position) {
        this.position = position;
        map.addPosition(position);
    }

    /**
     * Returns the current position of the drone.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Decreases the battery level by the specified cost.
     */
    public void decreaseBattery(int cost) {
        this.battery -= cost;
    }
    
    /**
     * Changes the drone's heading to a new direction and updates position accordingly.
     */
    public void changeHeading(Direction heading) {
        switch (direction) {
            case NORTH:
                if (heading == Direction.SOUTH || heading == Direction.NORTH) {
                    return;
                }
                position.setY(position.getY() + 3);
                position.setX(position.getX() + 3 * ((heading == Direction.WEST) ? -1 : 1));
                break;
            case EAST:
                if (heading == Direction.WEST || heading == Direction.EAST) {
                    return;
                }
                position.setX(position.getX() + 3);
                position.setY(position.getY() - 3 * ((heading == Direction.NORTH) ? -1 : 1));
                break;
            case SOUTH:
                if (heading == Direction.NORTH || heading == Direction.SOUTH) {
                    return;
                }
                position.setY(position.getY() - 3);
                position.setX(position.getX() + 3 * ((heading == Direction.WEST) ? -1 : 1));
                break;
            case WEST:
                if (heading == Direction.EAST || heading == Direction.WEST) {
                    return;
                }
                position.setX(position.getX() - 3);
                position.setY(position.getY() - 3 * ((heading == Direction.NORTH) ? -1 : 1));
                break;
        }
        direction = heading;
    }
    
    /**
     * Moves the drone 3 units in the current heading direction.
     * Updates the map with the new position.
     */
    public void fly() {
        // Calculate new position based on direction
        switch(direction) {
            case NORTH:
                position.setY(position.getY() - 3);
                break;
            case SOUTH:
                position.setY(position.getY() + 3);
                break;
            case EAST:
                position.setX(position.getX() + 3);
                break;
            case WEST:
                position.setX(position.getX() - 3);
                break;
        }
        
        // Register the new position in the map
        map.addPosition(position.getPosition());
        
        // If we have biome info from previous scan, update the map
        if (prevScanResult != null && getCurrentBiome() != null && !getCurrentBiome().equals("NONE")) {
            map.updateTerrain(position, TerrainType.valueOf(getCurrentBiome().toUpperCase()));
        }
    }

    /**
     * Creates a command with direction parameter.
     */
    public Command giveCommand(CommandOption command, Direction direction) {
        logger.info("Calliqdqddqdng CommandFactory.createCommand()");
        return CommandFactory.createCommand(command, direction);
    }

    /**
     * Creates a command without direction parameter.
     */
    public Command giveCommand(CommandOption command) {
        logger.info("Calling CommandFactory.createCommand()");
        return CommandFactory.createCommand(command);
    }

    /**
     * Returns the map being explored.
     */
    public Map getMap() {
        return map;
    }

    /**
     * Returns the current battery level.
     */
    public int getBattery() {
        return battery;
    }

    /**
     * Updates the map with scan results, including biome and points of interest.
     */
    public void scanIntoMap(ScanResult result) {
        // Update the terrain using the scanned biome.
        String currentBiome = (result.getBiomes().length() > 0) 
                ? result.getBiomes().getString(0) : "OCEAN";
                
        // Update the current position's biome so subsequent scans are aware of the change.
        position.setBiome(TerrainType.valueOf(currentBiome.toUpperCase()));
        map.updateTerrain(position, TerrainType.valueOf(currentBiome.toUpperCase()));
        
        // Store the result for later use (e.g. getCurrentBiome())
        prevScanResult = result;
        
        // Process detected creeks from the scan (if any)
        JSONArray creeks = result.getCreeks();
        for (int i = 0; i < creeks.length(); i++) {
            // Creeks may be strings or JSONObjects, handle both cases
            try {
                JSONObject creekJson = creeks.getJSONObject(i);
                // Create a new Position copy for uniqueness.
                POIType creek = POIType.CREEK;
                creek.setPosition(new Position(position.getX(), position.getY()));
                creek.setUID(creekJson.toString());
                map.addPointOfInterest(creek);
            } catch (Exception e) {
                // Handle string creek IDs
                String creekId = creeks.getString(i);
                POIType creek = POIType.CREEK;
                creek.setPosition(new Position(position.getX(), position.getY()));
                creek.setUID(creekId);
                map.addPointOfInterest(creek);
            }
        }
        
        // Process detected emergency sites similarly
        JSONArray sites = result.getSites();
        for (int i = 0; i < sites.length(); i++) {
            try {
                JSONObject siteJson = sites.getJSONObject(i);
                POIType site = POIType.EMERGENCY_SITE;
                site.setPosition(new Position(position.getX(), position.getY()));
                site.setUID(siteJson.toString());
                map.addPointOfInterest(site);
            } catch (Exception e) {
                // Handle string site IDs
                String siteId = sites.getString(i);
                POIType site = POIType.EMERGENCY_SITE;
                site.setPosition(new Position(position.getX(), position.getY()));
                site.setUID(siteId);
                map.addPointOfInterest(site);
            }
        }
    }

    /**
     * Returns the current biome the drone is in, or "NONE" if not available.
     */
    public String getCurrentBiome() {
        if (prevScanResult == null || prevScanResult.getBiomes().isEmpty()) {
            return "NONE";
        }
        return prevScanResult.getBiomes().get(0).toString();
    }
}
