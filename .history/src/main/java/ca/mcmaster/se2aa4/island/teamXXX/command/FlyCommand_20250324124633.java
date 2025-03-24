package ca.mcmaster.se2aa4.island.teamXXX.command;
import ca.mcmaster.se2aa4.island.teamXXX.*;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Direction;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Position;
import ca.mcmaster.se2aa4.island.teamXXX.results.CommandResult;

import org.json.JSONObject;

public class FlyCommand implements Command {
  
  public JSONObject createRequestJSON() {
    JSONObject request = new JSONObject();
    request.put("action", "fly");
    return request;
  }

  public void applyCommandResult(Drone drone, CommandResult result) {
    // adjust battery level
    // adjust position (+3 in heading direction)

    drone.decreaseBattery(result.getCost());

    Position curr_pos = drone.getPosition();



  }
  
  public CommandOption getCommandType() {
    return CommandOption.FLY;
  }

}
