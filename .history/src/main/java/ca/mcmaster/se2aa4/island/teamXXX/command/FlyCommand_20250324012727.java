package ca.mcmaster.se2aa4.island.teamXXX.command;
import ca.mcmaster.se2aa4.island.teamXXX.*;
import org.json.JSONObject;

public class FlyCommand implements Command {
  
  public JSONObject createRequestJSON() {
    JSONObject request = new JSONObject();
    request.put("action", "fly");
    return request;
  }

  public void applyCommandResult(Drone drone, CommandResult result) {
    // adjust battery level
    // adjust position (+1 in heading direction)

    drone.decreaseBattery() -= result.getCost();

  }
  
  public CommandOption getCommandType() {
    return CommandOption.FLY;
  }

}
