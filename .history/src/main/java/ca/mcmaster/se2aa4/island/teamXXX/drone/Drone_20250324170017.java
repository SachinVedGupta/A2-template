package ca.mcmaster.se2aa4.island.teamXXX.drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.ScanResult;
import ca.mcmaster.se2aa4.island.teamXXX.command.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class Drone {
    private Direction direction;
    private int battery;
    private Position position;
    private Map map;
    private CommandFactory commandFactory;

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
                position.setY(position.getY() - 3);
                position.setX(position.getX() + 3 * ((heading == Direction.WEST) ? -1 : 1));
                break;
            case EAST:
                if (heading == Direction.WEST || heading == Direction.EAST) {
                    return;
                }
                position.setX(position.getX() + 3);
                position.setY(position.getY() + 3 * ((heading == Direction.NORTH) ? -1 : 1));
                break;
            case SOUTH:
                if (heading == Direction.NORTH || heading == Direction.SOUTH) {
                    return;
                }
                position.setY(position.getY() + 3);
                position.setX(position.getX() + 3 * ((heading == Direction.WEST) ? -1 : 1));
                break;
            case WEST:
                if (heading == Direction.EAST || heading == Direction.WEST) {
                    return;
                }
                position.setX(position.getX() - 3);
                position.setY(position.getY() + 3 * ((heading == Direction.NORTH) ? -1 : 1));
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
        return CommandFactory.createCommand(command, direction);
    }

    public Command giveCommand(CommandOption command) {
        return CommandFactory.createCommand(command);
    }

    public Map getMap() {
        return map;
    }

    public int getBattery() {
        return battery;
    }

public void scanIntoMap(ScanResult result) {
    JSONArray creeks = result.getCreeks();
    JSONArray sites = result.getBiomes();

    for (int i = 0; i < creeks.length(); i++) {
        JSONObject creek = creeks.getJSONObject(i);
        POIType 
        map.addCreek(creek);
    }

    for (int i = 0; i < sites.length(); i++) {
        JSONObject site = sites.getJSONObject(i);
        map.addSite(site);
    }
}
}
