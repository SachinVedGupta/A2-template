package ca.mcmaster.se2aa4.island.teamXXX;

public class Drone {
    private Direction direction;
    private int battery;

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

    public 

    public void decreaseBattery(int cost) {
        this.battery -= cost;
    }
}
