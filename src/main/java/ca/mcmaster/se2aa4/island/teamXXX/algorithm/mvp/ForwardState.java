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
        String biome = getDrone().getCurrentBiome();
        if ("NONE".equalsIgnoreCase(biome)) {
            logger.info("No biome info available. Initiating scan to update terrain.");
            return getDrone().giveCommand(CommandOption.SCAN);
        } else if ("OCEAN".equalsIgnoreCase(biome)) {
            logger.info("Biome is still OCEAN. Continue flying.");
            return getDrone().giveCommand(CommandOption.FLY);
        } else {
            logger.info("Non-OCEAN biome detected (" + biome + "). Initiating scan for POIs.");
            return getDrone().giveCommand(CommandOption.SCAN);
        }
    }
}

