package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.teamXXX.command.Command;
import ca.mcmaster.se2aa4.island.teamXXX.command.CommandFactory;
import ca.mcmaster.se2aa4.island.teamXXX.command.CommandOption;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

/**
 * Implements a basic forward movement state for the drone.
 * This state aims to move the drone forward and scan when necessary.
 */
public class ForwardState extends State {
    private Command command = CommandFactory.createCommand(CommandOption.FLY);
    private final Logger logger = LogManager.getLogger();

    /**
     * Constructs a new ForwardState with the specified drone.
     * 
     * @param drone The drone to control
     */
    public ForwardState(Drone drone) {
        super(drone);
    }

    /**
     * Processes the result of a command and determines the next state.
     * In this implementation, it simply applies the command result and remains in the same state.
     * 
     * @param result The result of the previous command
     * @return The next state (this same state in this implementation)
     */
    @Override
    public State getNextState(CommandResult result) {
        Drone drone = getDrone();
        command.applyCommandResult(drone, result);

        return this;
    }

    /**
     * Determines the next command to issue based on the current biome.
     * - If no biome information is available (NONE), scan to get terrain data
     * - If in the ocean, continue flying
     * - If on land (non-OCEAN), scan for points of interest
     * 
     * @return The next command to execute
     */
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

