package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;
import ca.mcmaster.se2aa4.island.teamXXX.command.*;


public class ForwardState extends State {
    private Command command;
    private Drone drone;


    public ForwardState(Drone drone) {
        super(drone);
        command = drone.giveCommand(CommandOption.FLY);
    }

    @Override
    public State getNextState(CommandResult result) {
        return this;
    }

    @Override
    public Command getNextCommand() {
      if (command.getCommandType() == CommandOption.FLY) {
        return drone.giveCommand(CommandOption.SCAN);
      }
      else if  {
        return drone.giveCommand(CommandOption.FLY);
      }
      else (drone.getCurrentBiome() == "GROUND") {
        return drone.giveCommand(CommandOption.STOP);
      }
    }
}
