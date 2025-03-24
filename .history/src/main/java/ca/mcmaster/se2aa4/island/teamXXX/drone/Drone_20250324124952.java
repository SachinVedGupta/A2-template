package ca.mcmaster.se2aa4.island.teamXXX.drone;

public class Drone {
    private Direction direction;
    private int battery;
    private Position position;

    public Drone(Direction direction, int battery) {
        this.direction = direction;
        this.battery = battery;
    }
    public void setDirection (Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection() { //Leaky abstraction, refactor later??
        return direction;
    }

    public void setPosition(Position position) {
        this.position = position;
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
                position.setY(position.getY() - 3);
                position.setX(position.getX() + 3 * ((heading == Direction.WEST) ? -1 : 1));
                break;
            case EAST:
                position.setX(position.getX() + 3);
                position.setY(position.getY() + 3 * ((heading == Direction.NORTH) ? -1 : 1));
                break;
            case SOUTH:
                position.setY(position.getY() + 3);
                position.setX(position.getX() + 3 * ((heading == Direction.WEST) ? -1 : 1));
                break;
            case WEST:
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
        } else if (dir == Direction.SOUTH) {
            this.setPosition(new Position(curr_pos.getX(), curr_pos.getY() - 3));
          } else if (dir == Direction.WEST) {
            this.setPosition(new Position(curr_pos.getX() - 3, curr_pos.getY()));
          }
    }
}
