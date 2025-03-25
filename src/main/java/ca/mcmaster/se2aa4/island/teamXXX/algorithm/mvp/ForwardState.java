package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.teamXXX.command.Command;
import ca.mcmaster.se2aa4.island.teamXXX.command.CommandFactory;
import ca.mcmaster.se2aa4.island.teamXXX.command.CommandOption;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;


public class ForwardState extends State {
    private Command command = CommandFactory.createCommand(CommandOption.FLY);
    private final Logger logger = LogManager.getLogger();


    public ForwardState(Drone drone) {
        super(drone);
    }

    @Override
    public State getNextState(CommandResult result) {
      Drone drone = getDrone();
      command.applyCommandResult(drone, result);

      return this;
    }


    @Override
    public Command getNextCommand() {
        String currentBiome = getDrone().getCurrentBiome();
        // If the drone is now over land, change behavior (e.g. scan more or stop flying)
        if (!"OCEAN".equalsIgnoreCase(currentBiome)) {
            // Option: Issue a scan to further examine the terrain
            return getDrone().giveCommand(CommandOption.SCAN);
        }
        
        // Otherwise, alternately fly and scan as before
        if (command.getCommandType() == CommandOption.FLY) {
            command = CommandFactory.createCommand(CommandOption.SCAN);
            return getDrone().giveCommand(CommandOption.SCAN);
        } else {
            command = CommandFactory.createCommand(CommandOption.FLY);
            return getDrone().giveCommand(CommandOption.FLY);
        }
    }
}

