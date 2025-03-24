package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;

public class Algorithm extends BasicAlgorithm {
    public Algorithm(Drone drone) {
        super(drone);
    }

    @Override
    protected State getStartState(Drone drone) {
        return new ForwardState(drone);
    }
  
}
