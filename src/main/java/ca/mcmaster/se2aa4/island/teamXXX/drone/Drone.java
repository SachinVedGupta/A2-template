package ca.mcmaster.se2aa4.island.teamXXX.drone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.command.Command;
import ca.mcmaster.se2aa4.island.teamXXX.command.CommandFactory;
import ca.mcmaster.se2aa4.island.teamXXX.command.CommandOption;
import ca.mcmaster.se2aa4.island.teamXXX.results.ScanResult;

public class Drone {
    private Direction direction;
    private int battery;
    private Position position;
    private Map map;
    private CommandFactory commandFactory;
    private ScanResult prevScanResult;
    private final Logger logger = LogManager.getLogger();

    public Drone(Direction direction, int battery, Position position, Map map) {
        this.direction = direction;
        this.battery = battery;
        this.position = position;
        this.map = map;
        this.commandFactory = new CommandFactory();
    }
    public void setDirection (Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection() { //Leaky abstraction, refactor later??
        return direction;
    }

    public void setPosition(Position position) {
        this.position = position;
        map.addPosition(position);
    }

    public Position getPosition() {
        return position;
    }

    public void decreaseBattery(int cost) {
        this.battery -= cost;
    }
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
    public void fly() {
        // Move 3 units in the current heading direction.
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
    }

    public Command giveCommand(CommandOption command, Direction direction) {
        logger.info("Calliqdqddqdng CommandFactory.createCommand()");
        return CommandFactory.createCommand(command, direction);
    }

    public Command giveCommand(CommandOption command) {
        logger.info("Calling CommandFactory.createCommand()");
        return CommandFactory.createCommand(command);
    }

    public Map getMap() {
        return map;
    }

    public int getBattery() {
        return battery;
    }

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
            JSONObject creekJson = creeks.getJSONObject(i);
            // Create a new Position copy for uniqueness.
            POIType creek = POIType.CREEK;
            creek.setPosition(new Position(position.getX(), position.getY()));
            creek.setUID(creekJson.toString());
            map.addPointOfInterest(creek);
        }
        
        // Process detected emergency sites similarly
        JSONArray sites = result.getSites();
        for (int i = 0; i < sites.length(); i++) {
            JSONObject siteJson = sites.getJSONObject(i);
            POIType site = POIType.EMERGENCY_SITE;
            site.setPosition(new Position(position.getX(), position.getY()));
            site.setUID(siteJson.toString());
            map.addPointOfInterest(site);
        }
    }

    public String getCurrentBiome() {
        if (prevScanResult == null || prevScanResult.getBiomes().isEmpty()) {
            return "NONE";
        }
        return prevScanResult.getBiomes().get(0).toString();
    }
}
