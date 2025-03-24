package ca.mcmaster.se2aa4.island.teamXXX.drone;

public enum POIType {
    CREEK, EMERGENCY_SITE;

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}