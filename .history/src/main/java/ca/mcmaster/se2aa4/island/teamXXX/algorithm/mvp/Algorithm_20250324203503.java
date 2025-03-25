package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;

public class Algorithm extends BasicAlgo {
  
    public Algorithm(Drone drone) {
        super(drone);
        state = getStartState(drone);
    }

    @Override
    protected State getStartState(Drone drone) {
        return new ForwardState(drone);
    }
  
}
