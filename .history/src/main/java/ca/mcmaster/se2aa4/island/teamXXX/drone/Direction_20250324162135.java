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
                return "E";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            default:
                return "NONE";
        }
    }

    public static Direction fromString(String s) {
        switch (s) {
            case "N":
                return NORTH;
            case "E":
                return EAST;
            case "S":
                return SOUTH;
            case "W":
                return WEST;
            default:
                return null;
        }
    }
}
