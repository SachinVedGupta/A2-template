package ca.mcmaster.se2aa4.island.teamXXX;

public class Drone {
    private Direction direction;

    public Drone() {
        this.direction = Direction.EAST;
    }
    public void setDirection (Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection() { //Leaky abstraction, refactor later??
        return direction;
    }
}
