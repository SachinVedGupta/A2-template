package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

public class ForwardState extends State {
  

    public ForwardState(Drone drone) {
        super(drone);
    }

    @Override
    public State getNextState(CommandResult result) {
        drone.
    }

    @Override
    public Command getNextCommand() {
        return new ForwardCommand();
    }
}
