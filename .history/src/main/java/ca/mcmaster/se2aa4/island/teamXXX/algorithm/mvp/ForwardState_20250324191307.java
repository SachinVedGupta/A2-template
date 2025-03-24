package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;
import ca.mcmaster.se2aa4.island.teamXXX.command.*;


public class ForwardState extends State {
    private Command command;



    public ForwardState(Drone drone) {
        super(drone);
        command = new FlyCommand();
    }

    @Override
    public State getNextState(CommandResult result) {
        return this;
    }

    @Override
    public Command getNextCommand() {
      if (command.getCommandType() == CommandOption.FLY) {
        return new ScanCommand();
      else {
        return new FlyCommand()
      }
    }
}
