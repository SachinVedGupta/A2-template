package ca.mcmaster.se2aa4.island.teamXXX.algorithm.mvp;
import ca.mcmaster.se2aa4.island.teamXXX.command.Command;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

/**
 * Abstract base class for all drone states in the state pattern.
 * Represents a behavioral state of the drone and defines the interface
 * for state transitions and command decisions.
 */
public abstract class State {
    private Drone drone;    // The drone associated with this state

    /**
     * Constructs a new state for the specified drone.
     * 
     * @param drone The drone to associate with this state
     */
    public State(Drone drone) {
        this.drone = drone;
    }

    /**
     * Returns the drone associated with this state.
     * 
     * @return The drone object
     */
    public Drone getDrone() {
        return drone;
    }

    /**
     * Determines the next state based on the result of a command.
     * 
     * @param result The result of the previous command
     * @return The next state for the drone
     */
    public abstract State getNextState(CommandResult result);
    
    /**
     * Decides what command the drone should execute next in this state.
     * 
     * @return The command to execute
     */
    public abstract Command getNextCommand();
}
