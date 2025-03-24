package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;


public abstract class State {
    private Drone drone;

    public State(Drone drone) {
        this.drone = drone;
    }

    public Drone getDrone() {
        return drone;
    }

    public abstract State getNextState(ActionResult result);
    
    public abstract Action getNextAction();
}
