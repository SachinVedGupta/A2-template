package ca.mcmaster.se2aa4.island.teamXXX.drone;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    @Override
    public String toString() {
        switch (this) {
            case NORTH:
                return "N";
            case EAST:
                return "#";
            case SOUTH:
                return "SOUTH";
            case WEST:
                return "WEST";
            default:
                return "UNKNOWN";
        }
    }
}
