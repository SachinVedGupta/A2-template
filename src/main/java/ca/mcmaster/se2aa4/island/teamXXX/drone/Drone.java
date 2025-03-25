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
        Position curr_pos = this.getPosition();
        Direction dir = this.getDirection();

        if (dir == Direction.NORTH) {
            this.setPosition(new Position(curr_pos.getX(), curr_pos.getY() + 3));
        }
        else if (dir == Direction.EAST) {
            this.setPosition(new Position(curr_pos.getX() + 3, curr_pos.getY()));
        }
        else if (dir == Direction.SOUTH) {
            this.setPosition(new Position(curr_pos.getX(), curr_pos.getY() - 3));
        } 
        else if (dir == Direction.WEST) {
            this.setPosition(new Position(curr_pos.getX() - 3, curr_pos.getY()));
        }
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
        // Update the map with the current biome from the scan
        String currentBiome = (result.getBiomes().length() > 0)
            ? result.getBiomes().getString(0) : "OCEAN";
        // Assumes TerrainType enum names match the biome in uppercase
        map.updateTerrain(position, TerrainType.valueOf(currentBiome.toUpperCase()));
        
        // Store result for later use (e.g. getCurrentBiome())
        prevScanResult = result;
        
        // Process detected creeks from scan extras (if any)
        JSONArray creeks = result.getCreeks();
        for (int i = 0; i < creeks.length(); i++) {
            JSONObject creekJson = creeks.getJSONObject(i);
            POIType creek = POIType.CREEK;
            creek.setPosition(position);
            creek.setUID(creekJson.toString());
            map.addPointOfInterest(creek);
        }
        
        // Process detected emergency sites from scan extras (if any)
        JSONArray sites = result.getSites();
        for (int i = 0; i < sites.length(); i++) {
            JSONObject siteJson = sites.getJSONObject(i);
            POIType site = POIType.EMERGENCY_SITE;
            site.setPosition(position);
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
