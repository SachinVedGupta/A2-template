package ca.mcmaster.se2aa4.island.teamXXX.command;
import ca.mcmaster.se2aa4.island.teamXXX.*;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Direction;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Position;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlyCommand implements Command {

  // default constructor will automatically be present
  private final Logger logger = LogManager.getLogger();
  
  public JSONObject createRequestJSON() {
    logger.
    JSONObject request = new JSONObject();
    request.put("action", "fly");
    return request;
  }

  public void applyCommandResult(Drone drone, CommandResult result) {
    // adjust battery level
    // adjust position (+3 in heading direction)

    drone.decreaseBattery(result.getCost());
    drone.fly();
  }
  
  public CommandOption getCommandType() {
    return CommandOption.FLY;
  }

}
