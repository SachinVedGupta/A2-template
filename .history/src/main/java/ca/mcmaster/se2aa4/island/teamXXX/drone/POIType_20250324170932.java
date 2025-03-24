package ca.mcmaster.se2aa4.island.teamXXX.drone;

public enum POIType {
    CREEK, EMERGENCY_SITE;

    private Position position;
    private String uid;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getUID() {
        return this.uid;
    }

    public void setUID() {
        return position;
    }

}