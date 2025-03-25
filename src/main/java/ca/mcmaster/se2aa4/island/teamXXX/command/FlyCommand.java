package ca.mcmaster.se2aa4.island.teamXXX.command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

/**
 * Implements the FLY command which moves the drone in its current direction.
 * This command advances the drone 3 units in the direction it's currently facing.
 */
public class FlyCommand implements Command {

  // Default constructor will automatically be present
  private final Logger logger = LogManager.getLogger();
  
  /**
   * Creates the JSON request for the FLY command.
   * 
   * @return JSONObject containing the command parameters
   */
  public JSONObject createRequestJSON() {
    logger.info("CREATING THE FLY REQUEST");
    JSONObject request = new JSONObject();
    request.put("action", "fly");
    return request;
  }

  /**
   * Processes the result of the FLY command.
   * Updates the drone's position and depletes battery according to the cost.
   * 
   * @param drone The drone to update
   * @param result The command result containing cost and status
   */
  @Override
  public void applyCommandResult(Drone drone, CommandResult result) {
    // First fly to update position
    drone.fly();
    // Then decrease battery by cost
    drone.decreaseBattery(result.getCost());
  }
  
  /**
   * Returns the command type.
   * 
   * @return The FLY command type
   */
  public CommandOption getCommandType() {
    return CommandOption.FLY;
  }
}
